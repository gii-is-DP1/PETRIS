package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "users/loginForm";

    @RequestMapping("/login2")
    public String login(){
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }
    
}
