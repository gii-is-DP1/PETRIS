/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_FORM = "users/createUserForm";
	private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";
	private static final String VIEWS_USER_EDIT_PROFILE = "users/editProfile";

	private final UserService userService;
	private final PlayerService playerService;
	private final GameService gameService;

	
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserService clinicService, PlayerService playerService, GameService gameService, UserRepository userRepository) {
		this.userService = clinicService;
		this.playerService = playerService;
		this.gameService = gameService;
		this.userRepository = userRepository;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/* 
	
	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	@ResponseBody
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.userService.saveUser(user);
			
			return "redirect:/";
		}
	}
	*/

	@GetMapping(value = "/users/new")
    public ModelAndView initCreationForm() {
        ModelAndView mav = new ModelAndView(VIEWS_USER_CREATE_FORM);
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping(value = "/users/new")
    public ModelAndView processCreationForm(@Valid User user, BindingResult result) throws DataAccessException, DuplicatedUserNameException {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView(VIEWS_USER_CREATE_FORM);
            mav.addObject("user", user);
        } else {
			try {
				this.userService.saveUser(user);
				mav = new ModelAndView("welcome");
				mav.addObject("message", "User saved succesfully!");
				
			} catch (Exception DuplicatedUserNameException) {

            	mav = new ModelAndView(VIEWS_USER_CREATE_FORM);
				mav.addObject("message", "This name is already in use");
			}
        }
        return mav;
    }
/* 
	@GetMapping(value = "/users/{userId}/friends/{friendId}")
	public ModelAndView processFindUser(@PathVariable("friendId") String username, BindingResult result){
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject(this.userService.findUserByName(username));
		return mav;
	} 
	*/


	@GetMapping("/users/{userId}/personalStatistics")
	public String personalStatistics(ModelMap model) {
		String view = "users/pStatistics";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		Double wr = u.winrate();
		model.addAttribute("user", u);
		model.addAttribute("wr", wr);
		return view;

	}

	@GetMapping("/users/{userId}/record")
	public String record(ModelMap model) {
		String view = "users/userRecord";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		List<Player> players = userService.getPlayersByUser(u.getUsername());
		List<Game> games = gameService.getAllGames();
		List<Game> res = new ArrayList<Game>();
		for(Game g : games) {
			 for(Player p : players) {
				 if(p.equals(g.getPlayer1()) || p.equals(g.getPlayer2()))
				 res.add(g);
				}
		}
		model.addAttribute("players", players);
		model.addAttribute("res", res);
		model.addAttribute("user", u);
		return view;
	}
	@GetMapping("/users/{userId}/profile")
	public String profile(ModelMap model) {
		String view = "users/profile";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
		return view;

	}

	@GetMapping(value = "/users/{userId}/edit")
	public String editUser(ModelMap modelMap, @PathVariable("userId") String userId) {
		String view = VIEWS_USER_EDIT_PROFILE;
		User user = UserService.getUser(userId).get();
		boolean edit = true;
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("edit", edit);
		return view;
	}

	@PostMapping(value = "/users/{userId}/edit")
	public String processUpdateProfileForm(ModelMap modelMap, @PathVariable("userId") String userId, @Valid User user, BindingResult result) throws DuplicatedUserNameException{
		if (result.hasErrors()) {
			boolean edit = true;
			modelMap.put("edit", edit);
			return VIEWS_USER_EDIT_PROFILE;
		} else {
			User userEdited = userService.getUser(userId).get();
			userEdited.setUsername(user.getUsername());
			userEdited.setEmail(user.getEmail());
			userEdited.setPassword(user.getPassword());
			userService.saveUser(userEdited);
			return "redirect:/myProfile";
		}

	}

	@GetMapping("/login")
    public String login(){
        return "/users/loginForm";
    }

	@GetMapping("/users/{userId}")
    public String userInterface(ModelMap model){
		String view = "/users/userUI";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
        return view;
    }

	
	@GetMapping(value = "/users/{userId}/friends")
	public String initFriendForm(ModelMap modelMap) {
		String vista = "users/friendsUI";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUser(ud.getUsername()).get();
		List<User> amigos = userService.findAmigos(user.getUsername());
		modelMap.addAttribute("amigos", amigos);
		modelMap.addAttribute("user", user);
		return vista;
	}

	@GetMapping(path = "users/{userId}/friends/delete/{username}")
	public String eliminarAmigo(@PathVariable("username") String username, ModelMap modelMap) {

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUser(ud.getUsername()).get();
		modelMap.addAttribute("user", user);
		userService.borrarAmigo(user, username);
		return "redirect:/users/{userId}/friends";
	}

	@GetMapping("/users/{userId}/friends/search/{username}")
	public String friendDetails(@PathVariable("username") String username, ModelMap modelMap) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.getUser(ud.getUsername()).get();
		modelMap.addAttribute("user", u);
		User friend = this.userService.getUserByName(username);
		modelMap.addAttribute("friend", friend);
		Double wr = friend.winrate();
		modelMap.addAttribute("wr", wr);
		String view = "users/friendDetails";
		return view;
	}

	

	@GetMapping(value = "/users/{userId}/find")
	public String initFindForm(ModelMap model) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.getUserByName(ud.getUsername());
		model.addAttribute("user",user);
		model.put("user", new User());
		String vista = "users/findUsers";
		return vista;
	}

	@GetMapping(value = "/users/{userId}/findAll")
	public ModelAndView processFindForm(User user, BindingResult result, Map<String, Object> model) {
		ModelAndView mav;
		// allow parameterless GET request for /users to return all records
		if (user.username == null) {
			user.setUsername(""); // empty string signifies broadest possible search
		}

		
		Collection<User> results = this.userService.getUserByUsername(user.username);
		if (results.isEmpty()) {
			mav = new ModelAndView("users/userUI");
			mav.addObject("message", "Username " + user.username + " not found");
			
		}
		else if (results.size() == 1) {
			user = results.iterator().next();
			mav = new ModelAndView("redirect:/users/{userId}/" + user.getUsername());
		}
		else {
			model.put("selections", results);
			mav = new ModelAndView("/users/searchUsers");

		}
		return mav;
	}


	@GetMapping("/users/{userId}/{username}")
	public ModelAndView showUser(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject(this.userService.getUserByName(username));
		return mav;
	}



	


	

}
