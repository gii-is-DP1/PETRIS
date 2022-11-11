package org.springframework.samples.petclinic.token;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

// import antlr.StringUtils;

@Service
public class TokenService {

    private TokenRepository tokenRepository;

    public Token findTokenById(int tokenId) {
        return tokenRepository.findById(tokenId);
    }

    public void saveToken(Token tokenToUpdate) {
        tokenRepository.save(tokenToUpdate);
        /*
         * if(tokenToUpdate.getPlayer()!=null){
            Token otherToken=tokenToUpdate.getPlayer().getTokenwithIdDifferent(tokenToUpdate.getType(), tokenToUpdate.getId());
            if (tokenToUpdate.getSpace() == otherToken.getSpace() && otherToken.getId()!=tokenToUpdate.getId()) {            	           	
                throw new DuplicatedTokenSpaceException();
            }else
                tokenRepository.save(tokenToUpdate);                
        }else
            tokenRepository.save(tokenToUpdate);
         */
    }

    public Collection<TokenType> findTokenTypes() throws DataAccessException {
        return tokenRepository.findTokenTypes();
    }
    
}
