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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private final UserService userService;

	@Autowired
	public UserController(UserService clinicService) {
		this.userService = clinicService;
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
    public ModelAndView processCreationForm(@Valid User user, BindingResult result) {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView(VIEWS_USER_CREATE_FORM);
            mav.addObject("user", user);
        } else {
            this.userService.saveUser(user);
            mav = new ModelAndView("welcome");
			mav.addObject("message", "User saved succesfully!");
			
			
        }
        return mav;
    }


	@GetMapping("/users/{userId}/personalStatistics")
	public String personalStatistics(ModelMap model) {
		String view = "users/pStatistics";
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = UserService.findUser(ud.getUsername()).get();
		model.addAttribute("user", u);
		return view;

	}

	@GetMapping("/login")
    public String login(){
        return "/users/loginForm";
    }

	@GetMapping("/users/{userId}")
    public String userInterface(){
        return "/users/userUI";
    }

}
