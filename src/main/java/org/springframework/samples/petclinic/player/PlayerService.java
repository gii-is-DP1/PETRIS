package org.springframework.samples.petclinic.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    @Autowired
	public PlayerService(PlayerRepository feedingRepository) {
		this.playerRepository = feedingRepository;
	}

    private PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    @Transactional
    public void save(Player p){
        playerRepository.save(p);
    }
    
}
