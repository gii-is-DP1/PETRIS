package org.springframework.samples.petclinic.game;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    
    private final GameService gameService;
    private final PlayerService playerService;


    private static final String GAME_VIEW = "games/showGameInit";
    private static final String CREATE_GAME = "games/createGame";
    //private static final String GAME_LISTING = "games/gameListing";
    private static final String CURRENT_GAME = "games/playingGame";
    private static final String JOIN_BY_USERNAME = "games/askForUsername";


    @Autowired
	public GameController(GameService gameService,PlayerService playerService) {
		this.gameService = gameService;
        this.playerService =  playerService;
	}

    @GetMapping
    public String showGameInterface(ModelMap model) {
        return GAME_VIEW;
    }

    @GetMapping("/create")
    public String createGame(ModelMap model){
        model.put("game", new Game());
        return CREATE_GAME;
    }
    @PostMapping("/create")
    public String saveNewGame(@Valid Game game, BindingResult bindingResult, ModelMap model){
        if(bindingResult.hasErrors()){
            return CREATE_GAME;
        }else{
            Game newGame = new Game();
            BeanUtils.copyProperties(game, newGame, "id");
            Game createdGame = this.gameService.save(newGame);
            createdGame.createSpaces();
            model.put("message", "game with id " + createdGame.getId()+ " created successfully");
            return CURRENT_GAME;
        }
    }
    @GetMapping("/join")
    public String joinGame(String opponentUserName, String userName,  ModelMap model){
        try {
            Player player1 = this.playerService.getPlayerByUserId(opponentUserName);
            Player player2 = this.playerService.getPlayerByUserId(userName);
            Game game = this.gameService.getGameByPlayerId(player1.getId());
            if (game.isActive()){
                game.setPlayer2(player2);
            }else{
                model.put("message", "it doesn't exists any game" );
                return JOIN_BY_USERNAME;
            }
            
        } catch (Exception e) {
            model.put("message", "invalid username" + userName);
        }
        return JOIN_BY_USERNAME;
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
        Game activeGame= this.gameService.getGameById(gameId);    
        model.put("game", activeGame);
        return CURRENT_GAME;
    }
}
