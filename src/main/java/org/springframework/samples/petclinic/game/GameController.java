package org.springframework.samples.petclinic.game;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.samples.petclinic.Colour.ColourService;
import org.springframework.samples.petclinic.model.PetrisBoardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.token.ImpossibleMoveException;
import org.springframework.samples.petclinic.user.DuplicatedUserNameException;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private static final String GAMES_IN_PROGRESS_PAGE = "games/gamesInProgressP";
    private static final String FINISHED_GAME = "games/finishedGame";
    private static final String SPECTATE_GAME = "games/spectate";
    private static final String GAMES_TO_SPECTATE = "games/gameListingToSpectate";
    private static final String SPECTATE_BY_CODE = "games/spectateByCode";





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
            model.put("message", "Error:  " + e);
            return JOIN_BY_CODE;
        }
    }
    
    @GetMapping("/{gameId}")
    public String activeGame(ModelMap model, @PathVariable("gameId") Integer gameId, Integer space1Position, Integer space2Position, Integer numBacteriaToMove, HttpServletResponse response){

        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(ud.getUsername()).get();
        model.addAttribute("user",user);
                    
        Game activeGame= this.gameService.getGameById(gameId);

        response.addHeader("Refresh", "15");

        if(activeGame.getPlayer2() != null){
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
        }
        catch (Exception e){
            if(this.playerService.getPlayerHasMoved(gameId).isHasMoved() == true){
                model.put("message", "If you have already moved your tokens you should end your turn.");
            }
        }
        boolean showPassTurnButton = this.gameService.showPassTurnButton(activeGame, user.getUsername());
    
        model.addAttribute("code",activeGame.getCode());
        model.put("game", activeGame);
        model.addAttribute("showPassTurnButton", showPassTurnButton);
        model.put("petrisBoard", this.petrisBoardService.getByGameId(activeGame.getId()));

        return CURRENT_GAME;
    }
    
    @GetMapping("/{gameId}/passRound")
    public String passRound(ModelMap model,@PathVariable("gameId") Integer gameId) throws NotHisTurnException{
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(ud.getUsername()).get();
        model.addAttribute("user",user);

        Game activeGame= this.gameService.getGameById(gameId);
        
        String redirection = this.gameService.passRound(user.getUsername(), activeGame);
        
        return redirection;
    }

    @GetMapping("/{gameId}/leaveGame")
    public String leaveGame(ModelMap model, @PathVariable("gameId") Integer gameId){
        
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(ud.getUsername()).get();
        model.addAttribute("user",user);

        Game activeGame= this.gameService.getGameById(gameId);

        this.gameService.leaveGame(activeGame,user.getUsername());

        return "redirect:/games/" + activeGame.getId() + "/finishedGame";

    }
    
    @GetMapping("/{gameId}/finishedGame")
    public String finishedGame(ModelMap model,@PathVariable("gameId") Integer gameId) throws DataAccessException, DuplicatedUserNameException{

        if(this.gameService.getGameById(gameId)==null) {
            throw new RuntimeException("test exception");
        } else {
        
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
        
            Game game= this.gameService.getGameById(gameId);
            model.put("game", game);
            User winneruser = this.gameService.getWinnerUser(gameId);
            User user1 = this.gameService.getUser(gameId, winneruser, user);
            Integer points = this.gameService.getPointsGame(gameId, winneruser, user);
            model.addAttribute("user",user1);
            model.addAttribute("winnerUser", winneruser);
            model.addAttribute("pointOfTheGame", points);
            
            
            this.gameService.achievementsUpdateFinishedGame(gameId);
    
            return FINISHED_GAME;
        }
     }


    @PostMapping("/{gameId}/finishedGame")
    public String postfinishedGame(@Valid @ModelAttribute("user") User user,@PathVariable("gameId") Integer gameId,
    BindingResult bindingResult) throws DataAccessException, DuplicatedUserNameException {
        if (bindingResult.hasErrors()) {
            return FINISHED_GAME;
        } else {
            User winneruser = this.gameService.getWinnerUser(gameId);
            this.gameService.savUser(gameId, winneruser, user);
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


    @GetMapping("/spectate")
    public String spectateGame(ModelMap model){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
        return SPECTATE_GAME;
    }

    @GetMapping("/spectate/public")
    public String spectatePublicGame(ModelMap model,@PageableDefault(page = 0,size = 1) Pageable pg){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            model.addAttribute("user",user);

            Page<Game> games = this.gameService.getAllPlayingGamesPage(pg);
            model.addAttribute("listGames",games);
            return GAMES_TO_SPECTATE;
            
        } catch (Exception e) {
            model.put("message", "Error:  " + e);
            return SPECTATE_GAME;
        }
    }

    @GetMapping("/spectate/{gameCode}")
    public String spectateGameByCode(@PathVariable("gameCode") String gameCode,  ModelMap model){
        return spectatePrivateGame(gameCode, model);

    }

    @GetMapping("/spectate/private")
    public String spectatePrivateGame(String gameCode,  ModelMap model){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            
            model.addAttribute("user",user);
            Game activeGame = this.gameService.getGameByCode(gameCode);
            
            return "redirect:/games/" + activeGame.getId();

        }catch (Exception e) {
            model.put("message", "Invalid code");
            return SPECTATE_BY_CODE;
        }
    }
}