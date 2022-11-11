package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    
    @Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game getGameById(Integer id){
        return gameRepository.findGameByid(id);        
    }
    public List<Game> getAllActiveGames(){
        return gameRepository.findAllActiveGames();
    }

    public Game save(Game g){
        return gameRepository.save(g);
    }

}
