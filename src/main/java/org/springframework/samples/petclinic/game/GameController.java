package org.springframework.samples.petclinic.game;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {
    
    private final GameService gameService;

    private static final String GAME_VIEW = "game/showGameInit";
    private static final String CREATE_GAME = "game/createGame";
    private static final String GAME_LISTING = "game/gameListing";
    private static final String CURRENT_GAME = "game/currentGame";



    @Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
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
            model.put("message", "game with id " + createdGame.getId()+ " created successfully");
            return CREATE_GAME;
        }
    }
    @GetMapping("/join")
    public String joinGame(ModelMap model){
        model.put("games",this.gameService.getAllActiveGames());
        return GAME_LISTING;
    }
    
    @GetMapping("/playing")
    public String gameActive(ModelMap model, Integer gameId){
        Game activeGame= this.gameService.getGameById(gameId);    
        model.put("game", activeGame);
        return CURRENT_GAME;
    }

}
