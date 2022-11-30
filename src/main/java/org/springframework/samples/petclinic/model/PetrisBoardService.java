package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.token.Token;
import org.springframework.samples.petclinic.token.TokenService;
import org.springframework.samples.petclinic.token.TokenType;
import org.springframework.samples.petclinic.token.TokenTypeService;
import org.springframework.stereotype.Service;


@Service
public class PetrisBoardService {

	private final PetrisBoardRepository petrisBoardRepository;
	private final TokenTypeService tokenTypeService;
	private final PlayerService playerService;
	private final TokenService tokenService;

    @Autowired 
	public PetrisBoardService(PetrisBoardRepository petrisBoardRepository,TokenTypeService tokenTypeService,
									PlayerService playerService,TokenService tokenService) {
		this.petrisBoardRepository = petrisBoardRepository;
		this.tokenTypeService = tokenTypeService;
		this.playerService = playerService;
		this.tokenService = tokenService;
	}
	
	public Optional<PetrisBoard> getById(Integer id){
		return petrisBoardRepository.findById(id);
	}

	public PetrisBoard getByGameId(Integer gameId){
		return petrisBoardRepository.findByGameId(gameId);
	}

	public void save(PetrisBoard petrisBoard){
		petrisBoardRepository.save(petrisBoard);
	}

	public void createBoard(Game game){
		
		PetrisBoard newBoard = new PetrisBoard();

        List<Token> listTokenToAdd = new ArrayList<>();

        TokenType bacterium = this.tokenTypeService.getTokenTypeByName("bacterium");
        TokenType sarcina = this.tokenTypeService.getTokenTypeByName("sarcina");
		List<Player> players = this.playerService.getPlayersOfGame(game.getId());

		for (Player player : players){

			Integer numSarcina = 4;
        	Integer numBacteria = 20; 

			while(numBacteria!=0){
				Token newToken = new Token(); 
				newToken.setColour(player.getColour());
				newToken.setTokenType(bacterium);
				newToken.setPlayer(player);
				Token createdToken = this.tokenService.save(newToken);
				listTokenToAdd.add(createdToken);
				numBacteria--;
			}
			while(numSarcina!=0){
				Token newToken = new Token(); 
				newToken.setColour(player.getColour());
				newToken.setTokenType(sarcina);
				newToken.setPlayer(player);
				Token createdToken = this.tokenService.save(newToken);
				listTokenToAdd.add(createdToken);
				numSarcina--;
			}
		}
		newBoard.setGame(game);
		newBoard.setTokens(listTokenToAdd);
		this.save(newBoard);
    }
}
