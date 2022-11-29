package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.Colour.ColourService;
import org.springframework.samples.petclinic.chat.Chat;
import org.springframework.samples.petclinic.chat.ChatService;
import org.springframework.samples.petclinic.model.PetrisBoardService;
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
    private final PetrisBoardService petrisBoardService;
    private final ChatService chatService;


    private static final String GAME_VIEW = "games/showGameInit";
    private static final String CREATE_GAME = "games/createGame";
    private static final String GAME_LISTING = "games/gameListing";
    private static final String CURRENT_GAME = "games/playingGame";
    private static final String JOIN_BY_CODE = "games/joinByCode";


    @Autowired
	public GameController(GameService gameService,PlayerService playerService,UserService userService,ColourService colourService,PetrisBoardService petrisBoardService, ChatService chatService) {
		this.gameService = gameService;
        this.playerService =  playerService;
        this.userService =userService;
        this.colourService =colourService;
        this.petrisBoardService = petrisBoardService;
        this.chatService = chatService;
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
            
            this.gameService.setActiveGamesToFalse(user.getUsername());
            
            Colour colour = this.colourService.getColourByName(colourName);
            Player player1 = new Player(colour,0,0,0, user);
            Player createdPlayer = this.playerService.save(player1);
            
            Game newGame = new Game();
            game.setCode(this.gameService.generateCode());
            game.setPublic(isPublic);
            game.setActive(true);
            game.setPlayer1(createdPlayer);
            game.createSpaces();
            BeanUtils.copyProperties(game, newGame, "id");
            Game createdGame = this.gameService.save(newGame);
		    
            model.put("message", "game created successfully!. The game code is:" + createdGame.getCode());
            return "redirect:/games/" + createdGame.getId();
        }
    }
    @GetMapping("/join/private")
    public String joinPrivateGame(String gameCode,  ModelMap model){

        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.getUser(ud.getUsername()).get();
            
            model.addAttribute("user",user);

            Game game = this.gameService.getGameByCode(gameCode);
            String player1Colour = game.getPlayer1().getColour().getName();
            Colour randomColour = this.colourService.getOtherColoursExcept(player1Colour).get(0);

            if (game.getPlayer2()==null){
                
                Player player2 = new Player(randomColour, 0,0,0, user);
                Player createdPlayer = this.playerService.save(player2);
                game.setPlayer2(createdPlayer);
                Game createdGame = this.gameService.save(game);
                return "redirect:/games/" + createdGame.getId();

            }else{
                model.put("message", "This game is full" );
                return JOIN_BY_CODE;
            }   
        } catch (Exception e) {
            model.put("message", "invalid code   " + gameCode);
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
    public String activeGame(ModelMap model, @PathVariable("gameId") Integer gameId, HttpServletResponse response){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUser(ud.getUsername()).get();
        model.addAttribute("user",user);
   
        Game activeGame= this.gameService.getGameById(gameId);    
        model.addAttribute("code",activeGame.getCode());
        model.put("game", activeGame);
        model.put("petrisBoard", this.petrisBoardService.getById(1).get());

		response.addHeader("Refresh", "12");
		Game game = gameService.getGameById(gameId);
		Collection<Chat> res;

		res = this.chatService.getChatsById(game.getId());
		model.addAttribute("chats", res);

		model.addAttribute("NuevoMensaje", new Chat());
        return CURRENT_GAME;
    }

}