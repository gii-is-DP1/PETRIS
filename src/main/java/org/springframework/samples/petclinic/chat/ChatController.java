package org.springframework.samples.petclinic.chat;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    private final GameService gameService;
    private final UserService userService;
    private final ChatService chatService;


    @Autowired
	public ChatController(GameService gameService,UserService userService, ChatService chatService) {
		this.gameService = gameService;
        this.userService = userService;
        this.chatService = chatService;
	}

    @GetMapping(path = "/games/{gameId}/chat")
	public String listadoChats(ModelMap modelMap, @PathVariable("gameId") int gameId, HttpServletResponse response) {
		String vista = "chats/chat";
		response.addHeader("Refresh", "12");
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
		User user = userService.getUser(username).get();
		Game game = gameService.getGameById(gameId);
        User player1 = game.getPlayer1().getUser();
        User player2 = game.getPlayer2().getUser();
        modelMap.addAttribute("player1", player1);
        modelMap.addAttribute("player2", player2);


		modelMap.addAttribute("user", user);
		Collection<Chat> res;
		res = this.chatService.getChatsById(game.getId());
		modelMap.addAttribute("chats", res);

		modelMap.addAttribute("NuevoMensaje", new Chat());
		return vista;
	}

    @PostMapping("/games/{gameId}/chat/save")
    public String saveChat(@Valid Chat chat, @PathVariable("gameId") Integer gameId,ModelMap model){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ud.getUsername();
        Game game = gameService.getGameById(gameId);
        chat.setGame(game);
        User user1 = game.getPlayer1().getUser();
        User user2 = game.getPlayer2().getUser();
        if(username.equals(user1.getUsername())){
            chat.setUser(user1);
        }else if(username.equals(user2.getUsername())){
            chat.setUser(user2);
        }
        chatService.saveChat(chat);
        return "redirect:/games/" + game.getId() + "/chat";
    }



}
