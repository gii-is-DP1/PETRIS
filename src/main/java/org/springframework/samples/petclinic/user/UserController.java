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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.player.Player;
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
	private static final String VIEWS_USER_EDIT_PROFILE = "users/editProfile";

	private final UserService userService;
	private final GameService gameService;
	private final AuthoritiesRepository authoritiesRepository;

	
	

	@Autowired
	public UserController(UserService userService, GameService gameService, AuthoritiesRepository authoritiesRepository) {
		this.userService = userService;
		this.gameService = gameService;
		this.authoritiesRepository = authoritiesRepository;
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
		User u = this.userService.getUser(ud.getUsername()).get();
		Double wr = u.winrate();
		model.addAttribute("user", u);
		model.addAttribute("wr", wr);
		return view;

	}

	@GetMapping("/users/{userId}/record")
	public String record(ModelMap model) {
		String view = "users/userRecord";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = this.userService.getUser(ud.getUsername()).get();
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
		User u = this.userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
		return view;

	}

	@GetMapping(value = "/users/{userId}/edit")
	public String initUpdateUserForm(@PathVariable("userId") String userId, ModelMap model) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = this.userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
		return VIEWS_USER_EDIT_PROFILE;
	}

	@PostMapping(value = "/users/{userId}/edit")
	public String processUpdateOwnerForm(ModelMap modelMap, @Valid User user, BindingResult result,
			@PathVariable("userId") String userId) throws DataAccessException, DuplicatedUserNameException {
		String vista = "users/userUI";
		if (result.hasErrors()) {
			return "/users/editProfile";
		}else {
			try {
			UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User u = this.userService.getUser(ud.getUsername()).get();
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			//u.setUsername(user.getUsername());
			userService.saveUser(u);
			modelMap.put("message", "User " + user.username + " has been modified");
			modelMap.put("messageType", "warning");
				
			} catch (DuplicatedUserNameException e) {
				vista = "users/userUI";
				modelMap.put("message", "User " + user.username + " has been modified");
				modelMap.put("messageType", "warning");
			}
			return vista;
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
		User u = this.userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
		Authorities au = authoritiesRepository.findByName(u.username);
		model.addAttribute("au", au);
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
		User u = this.userService.getUser(ud.getUsername()).get();
		modelMap.addAttribute("user", u);
		User friend = this.userService.getUser(username).get();
		modelMap.addAttribute("friend", friend);
		Double wr = friend.winrate();
		modelMap.addAttribute("wr", wr);
		String view = "users/friendDetails";
		return view;
	}

	

	@GetMapping(value = "/users/{userId}/find")
	public String initFindForm(ModelMap model) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = this.userService.getUser(ud.getUsername()).get();
		model.addAttribute("usuActual",user1);
		model.put("user", new User());
		String vista = "users/findUsers";
		return vista;
	}

	@GetMapping(value = "/users/{userId}/findAll")
	public ModelAndView processFindForm(User user, BindingResult result, Map<String, Object> model) {
		ModelAndView mav;
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = this.userService.getUser(ud.getUsername()).get();
		// allow parameterless GET request for /users to return all records
		if (user.username == null) {
			user.setUsername(""); // empty string signifies broadest possible search
		}

		
		Collection<User> results = this.userService.getUserByUsername(user.username);
		if (results.isEmpty()) {
			mav = new ModelAndView("users/userUI");
			mav.addObject("message", "Username " + user.username + " not found");
			mav.addObject("messageType", "danger");
			
		}
		else if (results.size() == 1) {
			user = results.iterator().next();
			mav = new ModelAndView("redirect:/users/{userId}/" + user.getUsername());
			mav.addObject("usuActual", u);
		}
		else {
			model.put("selections", results);
			mav = new ModelAndView("/users/searchUsers");
			mav.addObject("usuActual", u);

		}
		return mav;
	}


	@GetMapping("/users/{userId}/{username}")
	public ModelAndView showUser(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = this.userService.getUser(ud.getUsername()).get();
		mav.addObject(this.userService.getUser(username).get());
		mav.addObject("usuActual",u);
		return mav;
	}

	@GetMapping("/users/{userId}/ranking")
	public String showRanking(ModelMap modelMap){
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = this.userService.getUser(ud.getUsername()).get();
		modelMap.addAttribute("user",u);
		List<User> userTOP = userService.getBestPlayers();
		modelMap.addAttribute("users", userTOP);
		String vista = "/users/showRanking";
		
		return vista;
	}

	@GetMapping("/registeredUser")
	public String showRegisteredUser(ModelMap model, @PageableDefault(page = 0,size = 3) Pageable pg){
		String vista = "users/registeredUser";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = this.userService.getUser(ud.getUsername()).get();
		model.addAttribute("user",u);
		Page<User> users = userService.getAllRegisteredUsers(pg);
		model.addAttribute("users", users);

		return vista;
	}

	@GetMapping("/registeredUser/{userId}")
	public String editUserAdmin(ModelMap model, @PathVariable("userId") String userId){
		String vista = "/users/editUserAdmin";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = this.userService.getUser(ud.getUsername()).get();
		model.addAttribute("user", u);
		User u1 = this.userService.getUser(userId).get();
		model.addAttribute("user1", u1);
		return vista;

	}

	@PostMapping("/registeredUser/{userId}/edit")
	public String processUpdateUserForm(ModelMap modelMap, @Valid User user, BindingResult result, 
		@PathVariable("userId") String userId) throws DataAccessException, DuplicatedUserNameException {
			String vista = "users/registeredUser";
			if (result.hasErrors()) {
				String vista2 = "users/editUserAdmin";
				return vista2;
			}else{
				try {
					User u1 = this.userService.getUser(userId).get();
					u1.setEmail(user.getEmail());
					u1.setPassword(user.getPassword());
					userService.saveUser(u1);
					modelMap.put("message", "User " + user.username + " has been modified");
					modelMap.put("messageType", "warning");
					
				} catch (DuplicatedUserNameException e) {
					modelMap.put("message", "User " + user.username + " has been modified");
					modelMap.put("messageType", "warning");
				}
				return vista;
			}
		}

		@GetMapping("/registeredUser/delete/{username}")
		public String deleteUser(@PathVariable("username") String username, ModelMap modelMap) {
			//UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//User user = userService.getUser(ud.getUsername()).get();
			//modelMap.addAttribute("user", user);
			Optional<User> user1 = Optional.of(userService.getUser(username).get());

			if(user1.isPresent()) {
				userService.delete(user1.get());
				modelMap.addAttribute("message", "user delete");
			}else{
				modelMap.addAttribute("message", "user not deleted");
			}
			
			
			return "redirect:/registeredUser";

			

			///if(u.isEnabled()){
				

			//}
		}


	


	

}
