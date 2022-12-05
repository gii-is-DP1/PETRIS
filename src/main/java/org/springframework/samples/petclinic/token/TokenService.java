package org.springframework.samples.petclinic.token;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetrisBoard;
import org.springframework.samples.petclinic.space.Space;
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

    public void moveTokens(Space space1, Space space2, Integer numBacteriaToMove, String colour) throws ImpossibleMoveException {
        
        try {
            List<Token> listToken = this.tokenRepository.findPossibleTokensToChangeOfSpace(space1.getId(), colour);
    
            while(numBacteriaToMove!=0){
                Token tokenToChange = listToken.get(numBacteriaToMove-1);
                tokenToChange.setSpace(space2);
                tokenToChange.setHasBeenUsed(true);
                this.save(tokenToChange);
                numBacteriaToMove--;
            }
    
            List<Token> tokensToOrderSpace1 = this.tokenRepository.findTokensOfSpace(space1.getId(), colour);
            Integer numTokensSpace1 = tokensToOrderSpace1.size();
            List<Token> tokensToOrderSpace2 = this.tokenRepository.findTokensOfSpace(space2.getId(), colour);
            Integer numTokensSpace2 = tokensToOrderSpace2.size();
    
            for (Token token :  tokensToOrderSpace1){
                token.setPositionInSpace(numTokensSpace1);
                this.save(token);
                numTokensSpace1++;
            }
            for (Token token :  tokensToOrderSpace2){
                token.setPositionInSpace(numTokensSpace2);
                this.save(token);
                numTokensSpace2++;
            }
            
        } catch (Exception e) {
            throw new ImpossibleMoveException();
        }
    }

    public Token getTokenToAddInBinaryFision(Integer petrisBoardId, String colour, String tokenType) {
        return this.tokenRepository.findTokensToAddInBinaryFision(petrisBoardId, colour, tokenType).get(0);
    }

    public List<Token> getTokensToQuit(Space space) {
        return this.tokenRepository.findTokensToQuit(space.getId());
    }


    
}
