package org.springframework.samples.petclinic.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
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

    public List<Player> getPlayersByUser(String username){
        return playerRepository.findPlayersByUser(username);
    }

    @Transactional
    public Player save(Player p){
        return playerRepository.save(p);
    }

    public List<Player> getPlayersOfGame(Integer id){
        return playerRepository.findPlayersOfGame(id);
    }

    public boolean isPlayerOfUser(Integer id, String username) {
        
        Player player =  playerRepository.findUserOfPlayer(id, username);
        if (player!=null){
            return true;
            
        }else{
            return false;
        }
    }

    public Player getPlayerById(Integer playerId) {
        return playerRepository.findPlayerById(playerId);
    }

    public void setFalsePlayersMove(Game activeGame) {
        Player player1 = activeGame.getPlayer1();
        Player player2 = activeGame.getPlayer2();

        player1.setHasMoved(false);
        player2.setHasMoved(false);

        this.save(player1);
        this.save(player2);
    }

    public Player getPlayerHasMoved(Integer gameId){
        return playerRepository.findPlayerHasMoved(gameId);
    }
}
