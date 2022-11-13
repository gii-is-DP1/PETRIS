package org.springframework.samples.petclinic.player;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {

    @GetMapping("/player/{playerId}")
    public String login(){
        return "/players/playerUI";
    }

    
    
}
