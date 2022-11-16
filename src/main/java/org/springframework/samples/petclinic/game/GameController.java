package org.springframework.samples.petclinic.game;

import java.util.List;

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
    private static final String JOIN_BY_CODE = "games/joinByCode";


    @Autowired
	public GameController(GameService gameService,PlayerService playerService,UserService userService,ColourService colourService) {
		this.gameService = gameService;
        this.playerService =  playerService;
        this.userService =userService;
        this.colourService =colourService;
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
    public String saveNewGame(String colourName, boolean isPublic, @Valid Game game, BindingResult bindingResult, @PathVariable("username") String userName,ModelMap model){
        if(bindingResult.hasErrors()){
            return CREATE_GAME;
        }else{
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUserByName(ud.getUsername());
            model.addAttribute("user", user);
            
            List<Player> playersList = this.playerService.getPlayersByUser(user.getUsername());
            for (Player player : playersList){
                Game checkGame = this.gameService.getGameByPlayerId(player.getId());
                if (checkGame.isActive()){
                    checkGame.setActive(false);
                }
            }
            
            Colour colour = this.colourService.getColourByName(colourName);
            Player player1 = new Player(colour,0,0,0, user);
            Player createdPlayer = this.playerService.save(player1);
            
            Game newGame = new Game();
            String code = this.gameService.generateCode();
            game.setCode(code);
            game.setPublic(isPublic);
            game.setActive(true);
            game.setPlayer1(createdPlayer);
            BeanUtils.copyProperties(game, newGame, "id");
            Game createdGame = this.gameService.save(newGame);
		    
            model.addAttribute("code", code);
            model.put("message", "game created successfully!. The game code is:" + createdGame.getCode());
            return CURRENT_GAME;
        }
    }
    
    @GetMapping("/join")
    public String joinGame(String gameCode,  ModelMap model){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUserByName(ud.getUsername());
            
            model.addAttribute("user",user);

            Game game = this.gameService.getGameByCode(gameCode);
            String player1Colour = game.getPlayer1().getColour().getName();
            Colour randomColour = this.colourService.getOtherColoursExcept(player1Colour).get(0);


            
            if (game.getPlayer2()==null){
                
                Player player2 = new Player(randomColour, 0,0,0, user);
                Player createdPlayer = this.playerService.save(player2);
                game.setPlayer2(createdPlayer);
                Game createdGame = this.gameService.save(game);
                model.addAttribute("code",gameCode);
                return activeGame(model, game.getId());

            }else{
                model.put("message", "it doesn't exist any game" );
                return JOIN_BY_CODE;
            }

            
        } catch (Exception e) {
            model.put("message", "invalid code   " + gameCode);
            return JOIN_BY_CODE;
        }

    }
    
    @GetMapping("/playing")
    public String activeGame(ModelMap model, Integer gameId){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByName(ud.getUsername());
        model.addAttribute("user",user);
        Game activeGame= this.gameService.getGameById(gameId);    
        model.put("game", activeGame);
        return CURRENT_GAME;
    }
}