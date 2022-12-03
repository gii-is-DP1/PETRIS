package org.springframework.samples.petclinic.token;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetrisBoard;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private TokenRepository tokenRepository;

    @Autowired
	public TokenService(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

    public Token findTokenById(int tokenId) {
        return tokenRepository.findById(tokenId);
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Collection<TokenType> findTokenTypes() throws DataAccessException {
        return tokenRepository.findTokenTypes();
    }

    public void saveAll(PetrisBoard createdBoard) {

        for (Token token : createdBoard.getTokens()){
            token.setPetrisBoard(createdBoard);
            this.save(token);
        }
    }

    
}
