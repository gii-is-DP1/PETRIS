package org.springframework.samples.petclinic.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenTypeService {
    
    private final TokenTypeRepository tokenTypeRepository;

    @Autowired 
	public TokenTypeService(TokenTypeRepository tokenTypeRepository) {
		this.tokenTypeRepository = tokenTypeRepository;
	}

    public TokenType getTokenTypeByName(String name){
        return tokenTypeRepository.findTokenTypeByName(name);        
    }
}
