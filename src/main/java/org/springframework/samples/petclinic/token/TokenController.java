package org.springframework.samples.petclinic.token;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/players/{playerId}")
public class TokenController {

    private static final String VIEWS_TOKEN_CREATE_OR_UPDATE_FORM = "token/createOrUpdatePetForm";

	private final TokenService tokenService;
    //private final PlayerService playerService;

	@Autowired
	public TokenController(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@ModelAttribute("types")
	public Collection<TokenType> populateTokenTypes() {
		return this.tokenService.findTokenTypes();
	}
        
        /*@ModelAttribute("pet")
	public Pet findPet(@PathVariable("petId") Integer petId) {
            Pet result=null;
		if(petId!=null)
                    result=this.clinicService.findPetById(petId);
                else
                    result=new Pet();
            return result;
	}*/
                
	@InitBinder("player")
	public void initPlayerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("token")
	public void initTokenBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new TokenValidator());
	}
	/*
	 * @GetMapping(value = "/tokens/new")
	public String initCreationForm(Player player, ModelMap model) {
		Token token = new Token();
		player.addToken(token);
		model.put("token", token);
		return VIEWS_TOKEN_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/token/new")
	public String processCreationForm(Player player, @Valid Token token, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("token", token);
			return VIEWS_TOKEN_CREATE_OR_UPDATE_FORM;
		}
		else {
			try{
				player.addToken(token);
				this.tokenService.saveToken(token);
			}catch(DuplicatedPetNameException ex){
				result.rejectValue("name", "duplicate", "already exists");
				return VIEWS_TOKEN_CREATE_OR_UPDATE_FORM;
			}
			return "redirect:/players/{playerId}";
		}
	}

	 */

	@GetMapping(value = "/tokens/{tokenId}/edit")
	public String initUpdateForm(@PathVariable("tokenId") int tokenId, ModelMap model) {
		Token token = this.tokenService.findTokenById(tokenId);
		model.put("token", token);
		return VIEWS_TOKEN_CREATE_OR_UPDATE_FORM;
	}

    /**
     *
     * @param token
     * @param result
     * @param tokenId
     * @param model
     * @param player
     * @param model
     * @return
     */
        @PostMapping(value = "/tokens/{tokenId}/edit")
	public String processUpdateForm(@Valid Token token, BindingResult result, Player player,@PathVariable("tokenId") int tokenId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("token", token);
			return VIEWS_TOKEN_CREATE_OR_UPDATE_FORM;
		}
		else {
			Token tokenToUpdate=this.tokenService.findTokenById(tokenId);
			BeanUtils.copyProperties(token, tokenToUpdate, "id","player");                                                                                  
			this.tokenService.saveToken(tokenToUpdate);
			return "redirect:/players/{playerId}";
		}
	}
}
