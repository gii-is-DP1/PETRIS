package org.springframework.samples.petclinic.token;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.PetrisBoard;
import org.springframework.samples.petclinic.model.PetrisBoardRepository;
import org.springframework.samples.petclinic.space.Space;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private PetrisBoardRepository petrisBoardRepository;

    @Autowired
	public TokenService(TokenRepository tokenRepository,PetrisBoardRepository petrisBoardRepository) {
		this.tokenRepository = tokenRepository;
        this.petrisBoardRepository = petrisBoardRepository;
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

    public void moveTokens(Space space1, Space space2, Integer numBacteriaToMove, String colour, Integer petrisBoardId) {
        
            List<Token> listTokenSpace1 = this.tokenRepository.findPossibleTokensToChangeOfSpace(space1.getId(), colour);
    
            if (colour.equals("red")){
                if (space2.getNumRedBacteria()+numBacteriaToMove >4){
                    
                    List<Token> listTokenSpace2ToDelete = this.tokenRepository.findTokensToChangeForSarcina(space2.getId(), colour);
                    for (Token token: listTokenSpace2ToDelete){
                        token.setSpace(null);
                        token.setPositionInSpace(0);
                        this.save(token);
                    }

                    while(numBacteriaToMove!=0){
                        Token bacteriaToDelete = listTokenSpace1.get(numBacteriaToMove-1);
                        bacteriaToDelete.setSpace(null);
                        bacteriaToDelete.setPositionInSpace(0);
                        this.save(bacteriaToDelete);
                        numBacteriaToMove--;
                    }

                    Token sarcinaToAdd = this.tokenRepository.findUnusedSarcina(petrisBoardId,colour,"sarcina").get(0);
                    sarcinaToAdd.setSpace(space2);
                    sarcinaToAdd.setPositionInSpace(1);
                    this.save(sarcinaToAdd);

                }else{
                    while(numBacteriaToMove!=0){
                        Token tokenToChange = listTokenSpace1.get(numBacteriaToMove-1);
                        tokenToChange.setSpace(space2);
                        tokenToChange.setHasBeenUsed(true);
                        this.save(tokenToChange);
                        numBacteriaToMove--;
                    }
                }

            }else {
                if (space2.getNumBlackBacteria()+numBacteriaToMove >4){
                    
                    List<Token> listTokenSpace2ToDelete = this.tokenRepository.findTokensToChangeForSarcina(space2.getId(), colour);
                    for (Token token: listTokenSpace2ToDelete){
                        token.setSpace(null);
                        token.setPositionInSpace(0);
                        this.save(token);
                    }

                    while(numBacteriaToMove!=0){
                        Token bacteriaToDelete = listTokenSpace1.get(numBacteriaToMove-1);
                        bacteriaToDelete.setSpace(null);
                        bacteriaToDelete.setPositionInSpace(0);
                        this.save(bacteriaToDelete);
                        numBacteriaToMove--;
                    }

                    Token sarcinaToAdd = this.tokenRepository.findUnusedSarcina(petrisBoardId,colour,"sarcina").get(0);
                    sarcinaToAdd.setSpace(space2);
                    sarcinaToAdd.setPositionInSpace(1);
                    this.save(sarcinaToAdd);

                }else{
                    while(numBacteriaToMove!=0){
                        Token tokenToChange = listTokenSpace1.get(numBacteriaToMove-1);
                        tokenToChange.setSpace(space2);
                        tokenToChange.setHasBeenUsed(true);
                        this.save(tokenToChange);
                        numBacteriaToMove--;
                    }
                }
            }
            this.orderSpaces(space1, space2, colour);
    
               
    }
    
    public void orderSpaces(Space space1, Space space2, String colour){

        List<Token> tokensToOrderSpace1 = this.tokenRepository.findTokensOfSpace(space1.getId(), colour);
        Integer numTokensSpace1 = tokensToOrderSpace1.size();
        Integer cont1 = 0;

        List<Token> tokensToOrderSpace2 = this.tokenRepository.findTokensOfSpace(space2.getId(), colour);
        Integer numTokensSpace2 = tokensToOrderSpace2.size();
        Integer cont2 = 0;

        for (Token token :  tokensToOrderSpace1){
            if (cont1 < numTokensSpace1){
                cont1++;
            }
            token.setPositionInSpace(cont1);
            this.save(token);
        }
        for (Token token :  tokensToOrderSpace2){
            if (cont2 < numTokensSpace2){
                cont2++;
            }
            token.setPositionInSpace(cont2);
            this.save(token);
        }    
    }

    public Token getTokenToAddInBinaryFision(Integer petrisBoardId, String colour, String tokenType) {
        return this.tokenRepository.findTokensToAddInBinaryFision(petrisBoardId, colour, tokenType).get(0);
    }

    public List<Token> getTokensToQuit(Space space) {
        return this.tokenRepository.findTokensToQuit(space.getId());
    }

    public void setAllHasBeenUsedToFalse(Game activeGame) {
        PetrisBoard petrisBoard = this.petrisBoardRepository.findByGameId(activeGame.getId());
        List<Token> gameTokens = this.tokenRepository.findTokensByPetrisBoardId(petrisBoard.getId());

        for(Token token : gameTokens){
            token.setHasBeenUsed(false);
            this.save(token);
        }

    }   
}
