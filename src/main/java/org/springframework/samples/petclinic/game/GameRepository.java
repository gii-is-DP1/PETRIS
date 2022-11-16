package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Integer>{

    List<Game> findAll();

    @Query("SELECT g FROM Game g WHERE g.id = ?1")
    Game findGameByid(Integer id);

    @Query("SELECT g FROM Game g WHERE g.isActive = true AND g.isPublic = true AND g.player2 = null")
    List<Game> findAllPublicActiveEmptyGames();

    
    @Query("SELECT g FROM Game g WHERE g.player1.id = ?1 OR g.player2.id = ?1")
    Game getGameByPlayerId(Integer id);

    @Query("SELECT g FROM Game g WHERE g.player1.user.username = ?1 AND g.isActive = TRUE")
    Game findActiveGameByPlayer(String username);

    @Query("SELECT g FROM Game g WHERE g.code = ?1")
    Game findGameByCode(String gameCode);
    
}
