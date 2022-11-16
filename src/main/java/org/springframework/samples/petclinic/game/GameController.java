package org.springframework.samples.petclinic.game;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.Colour.ColourService;
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
    private final ColourService colourService;



    private static final String GAME_VIEW = "games/showGameInit";
    private static final String CREATE_GAME = "games/createGame";
    //private static final String GAME_LISTING = "games/gameListing";
    private static final String CURRENT_GAME = "games/playingGame";
    private static final String JOIN_BY_USERNAME = "games/askForUsername";


    @Autowired
	public GameController(GameService gameService,PlayerService playerService,UserService userService,ColourService colourService) {
		this.gameService = gameService;
        this.playerService =  playerService;
        this.userService =userService;
        this.colourService =colourService;
	}

    @GetMapping
    public String showGameInterface(ModelMap model) {
        return GAME_VIEW;
    }

    @GetMapping("/create/{userName}")
    public String createGame(ModelMap model){
        model.put("game", new Game());
        return CREATE_GAME;
    }
    @PostMapping("/create/{username}")
    public String saveNewGame(String colourName, boolean isPublic, @Valid Game game, BindingResult bindingResult, @PathVariable("username") String userName,ModelMap model){
        if(bindingResult.hasErrors()){
            return CREATE_GAME;
        }else{
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUserByName(ud.getUsername());
            /*
            List<Player> playersList = this.playerService.getPlayersByUser(user.getUsername());
            for (Player player : playersList){
                Game checkGame = this.gameService.getGameByPlayerId(player.getId());
                if (checkGame.isActive()){
                    checkGame.setActive(false);
                }
            }
            */
            Colour colour = this.colourService.getColourByName(colourName);
            Player player1 = new Player(colour,0,0,0, user);
            Player createdPlayer = this.playerService.save(player1);

            Game newGame = new Game();
            game.setPublic(isPublic);
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
            User userWhoJoins = this.userService.getUserByName(ud.getUsername());

            User userWhoCreatedGame = this.userService.getUserByName(opponentUserName);
            Player playerWhoCreatedGame = this.playerService.getPlayersByUser(userWhoCreatedGame.getUsername()).get(-1);

            Colour randomColour = this.colourService.getOtherColoursExcept(playerWhoCreatedGame.getColour().getName()).get(0);
            
            Player playerWhoJoins = new Player(randomColour, 0,0,0, userWhoJoins);
            Player createdPlayer = this.playerService.save(playerWhoJoins);
            
            Game game = this.gameService.getActiveGameByPlayer(opponentUserName);
            if (game.getPlayer2()==null){
                game.setPlayer2(createdPlayer);
                return activeGame(model, game.getId());


            }else{
                model.put("message", "it doesn't exist any game" );
                return JOIN_BY_USERNAME;
            }

            
        } catch (Exception e) {
            model.put("message", "invalid username" + opponentUserName);
            return JOIN_BY_USERNAME;
        }

    }
    
    @GetMapping("/playing")
    public String activeGame(ModelMap model, Integer gameId){
        Game activeGame= this.gameService.getGameById(gameId);    
        model.put("game", activeGame);
        return CURRENT_GAME;
    }
}