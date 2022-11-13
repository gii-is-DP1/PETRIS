package org.springframework.samples.petclinic.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    
    private PlayerRepository playerRepository;

    @Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

    public Player getPlayerByUserId(String userName){
        return playerRepository.findPlayerByUserId(userName);
    }


    @Transactional(readOnly = true)
    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    @Transactional
    public void save(Player p){
        playerRepository.save(p);
    }
    
}
