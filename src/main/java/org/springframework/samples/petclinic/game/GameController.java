package org.springframework.samples.petclinic.game;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.samples.petclinic.Colour.ColourService;
import org.springframework.samples.petclinic.model.PetrisBoardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.token.ImpossibleMoveException;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController{
    
    private final GameService gameService;
    private final UserService userService;
    private final PetrisBoardService petrisBoardService;
    private final PlayerService playerService;
    


    private static final String GAME_VIEW = "games/showGameInit";
    private static final String CREATE_GAME = "games/createGame";
    private static final String GAME_LISTING = "games/gameListing";
    private static final String CURRENT_GAME = "games/playingGame";
    private static final String JOIN_BY_CODE = "games/joinByCode";
    private static final String GAMES_IN_PROGRESS = "games/gamesInProgress";
    private static final String GAMES_IN_PROGRESS_PAGE = "games/gamesInProgressP";
    private static final String FINISHED_GAME = "games/finishedGame";


    @Autowired
	public GameController(GameService gameService, UserService userService,ColourService colourService,PetrisBoardService petrisBoardService,PlayerService playerService) {
		this.gameService = gameService;
        this.userService =userService;
        this.petrisBoardService = petrisBoardService;
        this.playerService = playerService;

	}

    @GetMapping
    public String showGameInterface(ModelMap model) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
        return GAME_VIEW;
    }

    @GetMapping("/create/{userName}")
    public String createGame(ModelMap model){
        model.put("game", new Game());
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
        return CREATE_GAME;
    }
        
    @PostMapping("/create/{username}")
    public String saveNewGame(String colourName, boolean isPublic, @Valid Game game, BindingResult bindingResult, @PathVariable("username") String userName,ModelMap model){
        
        if(bindingResult.hasErrors()){
            return CREATE_GAME;
        }else{
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            model.addAttribute("user", user);
            
            Game createdGame = this.gameService.createGame(game, user, colourName, isPublic);
		    
            return "redirect:/games/" + createdGame.getId();
        }
    }
    
    @GetMapping("/join/private")
    public String joinPrivateGame(String gameCode,  ModelMap model){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            
            model.addAttribute("user",user);
            
            return this.gameService.fillGame(gameCode, user);

        }catch(FullGameException e){
            model.put("message", "This game is full");
            return JOIN_BY_CODE;

        }catch(InvalidCodeException e){
            model.put("message", "Invalid code");
            return JOIN_BY_CODE;

        }catch (Exception e) {
            return JOIN_BY_CODE;
        }
    }
    
    @GetMapping("/join/{gameCode}")
    public String joinGameByCode(@PathVariable("gameCode") String gameCode,  ModelMap model){
        return joinPrivateGame(gameCode, model);

    }
    
    @GetMapping("/join/public")
    public String joinPublicGame(ModelMap model){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            model.addAttribute("user",user);

            List<Game> games = this.gameService.getAllPublicActiveEmptyGames();
            model.addAttribute("games",games);
            return GAME_LISTING;
            
        } catch (Exception e) {
            model.put("message", "invalid code   ");
            return JOIN_BY_CODE;
        }
    }
    
    @GetMapping("/{gameId}")
    public String activeGame(ModelMap model, @PathVariable("gameId") Integer gameId, Integer space1Position, Integer space2Position, Integer numBacteriaToMove, HttpServletResponse response){

        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(ud.getUsername()).get();
        model.addAttribute("user",user);
                    
        Game activeGame= this.gameService.getGameById(gameId);

        if(this.gameService.getGameById(gameId).getPlayer1() != null && this.gameService.getGameById(gameId).getPlayer2() != null){
            List<Player> players = playerService.getPlayersOfGame(gameId);
            model.addAttribute("players", players);
        }
        
        try {
            this.gameService.makeMove(user.getUsername(), activeGame, space1Position, space2Position, numBacteriaToMove);
        }
        catch (IncompleteGameException e) {
            model.put("message", "Waiting for another player.");
        }
        catch (ImpossibleMoveException i){
            model.put("message", "You can't make this move, try another one.");
        }catch (Exception e){
            if(this.playerService.getPlayerHasMoved(gameId).isHasMoved() == true){
                model.put("message", "if you have already moved your tokens you should end your turn");
            }else{

            }
        }
    
        model.addAttribute("code",activeGame.getCode());
        model.put("game", activeGame);
        model.put("petrisBoard", this.petrisBoardService.getByGameId(activeGame.getId()));

        return CURRENT_GAME;
    }
    @GetMapping("/{gameId}/passRound")
    public String passRound(ModelMap model,@PathVariable("gameId") Integer gameId) throws NotHisTurnException{
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(ud.getUsername()).get();
        model.addAttribute("user",user);

        Game activeGame= this.gameService.getGameById(gameId);
        String redirection ="redirect:/games/" + activeGame.getId();
        try{
            redirection = this.gameService.passRound(user.getUsername(), activeGame);
        }catch(NoMoveException e){
            model.put("message", "You have to make a move to pass turn.");
        }catch(NotHisTurnException e){
            model.put("message", "It's not your turn");
        }

        return redirection;
    }
    @GetMapping("/{gameId}/finishedGame")
    public String finishedGame(ModelMap model,@PathVariable("gameId") Integer gameId){
        if(this.gameService.getGameById(gameId)==null) {
            throw new RuntimeException("test exception");
        } else {

            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            
    
            Game game= this.gameService.getGameById(gameId);
            model.put("game", game);
            User winneruser = new User();
            Integer points = null;
            if(game.getWinner().equals("red")) {
                if(game.getPlayer1().getColour().getName().equals("red")) {
                    winneruser = game.getPlayer1().getUser();
                    if(user.getUsername().equals(winneruser.getUsername())) {
                        points = 15;
                        user.setPoints(user.getPoints() + points);
                    } else if(user.getUsername().equals(game.getPlayer2().getUser().getUsername())) {
                        points = -10;
                        user.setPoints(user.getPoints() + points);
                    }
                } else {
                    winneruser = game.getPlayer2().getUser();
                    if(user.getUsername().equals(winneruser.getUsername())) {
                        points = 15;
                        user.setPoints(user.getPoints() + points);
                    } else if(user.getUsername().equals(game.getPlayer1().getUser().getUsername())) {
                        points = -10;
                        user.setPoints(user.getPoints() + points);
                    }
                }
            } else {
                if(game.getPlayer1().getColour().getName().equals("red")) {
                    winneruser = game.getPlayer2().getUser();
                    if(user.getUsername().equals(winneruser.getUsername())) {
                        points = 15;
                        user.setPoints(user.getPoints() + points);
                    } else if(user.getUsername().equals(game.getPlayer1().getUser().getUsername())) {
                        points = -10;
                        user.setPoints(user.getPoints() + points);
                    }
                } else {
                    winneruser = game.getPlayer1().getUser();
                    if(user.getUsername().equals(winneruser.getUsername())) {
                        points = 15;
                        user.setPoints(user.getPoints() + points);
                    } else if(user.getUsername().equals(game.getPlayer2().getUser().getUsername())) {
                        points = -10;
                        user.setPoints(user.getPoints() + points);
                    }
                }
            }

            gameService.achievementsUpdateFinishedGame(gameId);
            model.addAttribute("user",user);
            model.addAttribute("winnerUser", winneruser);
            model.addAttribute("pointOfTheGame", points);
    
            return FINISHED_GAME;
        }
    }

    @GetMapping("/playing")
    public String listAllPlayingGamesPage(ModelMap model, @PageableDefault(page = 0,size = 1) Pageable pg){

        Page<Game> lista = gameService.getAllPlayingGamesPage(pg);
        model.addAttribute("listGames", lista);

        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		User user = userService.getUser(username).get();
        model.addAttribute("user", user);

        return GAMES_IN_PROGRESS_PAGE;
    }

    @GetMapping("/finished")
    public String listAllFinishedGames(ModelMap model){
        List<Game> listGames = gameService.getAllFinishedGames();
        model.addAttribute("listGames", listGames);

        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		User user = userService.getUser(username).get();
        model.addAttribute("user", user);

        return "games/finishedGames";
    }

}