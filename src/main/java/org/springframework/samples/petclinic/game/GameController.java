package org.springframework.samples.petclinic.game;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
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
public class GameController {
    
    private final GameService gameService;
    private final PlayerService playerService;
    private final UserService userService;


    private static final String GAME_VIEW = "games/showGameInit";
    private static final String CREATE_GAME = "games/createGame";
    //private static final String GAME_LISTING = "games/gameListing";
    private static final String CURRENT_GAME = "games/playingGame";
    private static final String JOIN_BY_USERNAME = "games/askForUsername";


    @Autowired
	public GameController(GameService gameService,PlayerService playerService,UserService userService) {
		this.gameService = gameService;
        this.playerService =  playerService;
        this.userService =userService;
	}

    @GetMapping
    public String showGameInterface(ModelMap model) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
        return GAME_VIEW;
    }

    @GetMapping("/create/{userName}")
    public String createGame(ModelMap model){
        model.put("game", new Game());
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
        return CREATE_GAME;
    }
    @PostMapping("/create/{username}")
    public String saveNewGame( @Valid Game game, BindingResult bindingResult, @PathVariable("username") String userName,ModelMap model){
        if(bindingResult.hasErrors()){
            return CREATE_GAME;
        }else{
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUserByName(ud.getUsername());
            model.addAttribute("user", user);
            /*
            List<Player> playersList = this.playerService.getPlayersByUser(user.getUsername());
            for (Player player : playersList){
                Game checkGame = this.gameService.getGameByPlayerId(player.getId());
                if (checkGame.isActive()){
                    checkGame.setActive(false);
                }
            }
            */
            Player player1 = new Player(0,0,0, user);
            Player createdPlayer = this.playerService.save(player1);

            Game newGame = new Game();
            game.setActive(true);
            game.setPlayer1(createdPlayer);
            BeanUtils.copyProperties(game, newGame, "id");
            Game createdGame = this.gameService.save(newGame);
		    

            model.put("message", "game with id " + createdGame.getId()+ " created successfully");
            return CURRENT_GAME;
        }
    }
    @GetMapping("/join")
    public String joinGame(String opponentUserName,  ModelMap model){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUserByName(ud.getUsername());
            model.addAttribute("user",user);
            Player player2 = new Player(0,0,0, user);
            Player createdPlayer = this.playerService.save(player2);

            User user1 = this.userService.getUserByName(opponentUserName);
            Player player1 = this.playerService.getPlayersByUser(user1.getUsername()).get(-1);
            Game game = this.gameService.getGameByPlayerId(player1.getId());
            if (game.isActive() && game.getPlayer2()==null){
                game.setPlayer2(createdPlayer);
                return CURRENT_GAME;

            }else{
                model.put("message", "it doesn't exist any game" );
                return JOIN_BY_USERNAME;
            }
            
        } catch (Exception e) {
            model.put("message", "invalid username" + opponentUserName);
            return JOIN_BY_USERNAME;
        }
    }
/*
    @GetMapping("/join")
    public String joinGame1(String opponentUserName,  ModelMap model){
        try {
            Player player1 = this.playerService.getPlayerByUserId(opponentUserName);
            Game game = this.gameService.getGameByPlayerId(player1.getId());
            if (game.isActive()){
                game.setPlayer2(player2);
            }else{
                model.put("message", "it doesn't exists any game" );
                return JOIN_BY_USERNAME;
            }
            
        } catch (Exception e) {
            model.put("message", "invalid username");
        }
        return JOIN_BY_USERNAME;
    }
    */
    
    @GetMapping("/playing")
    public String gameActive(ModelMap model, Integer gameId){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByName(ud.getUsername());
        model.addAttribute("user",user);
        Game activeGame= this.gameService.getGameById(gameId);    
        model.put("game", activeGame);
        return CURRENT_GAME;
    }
}