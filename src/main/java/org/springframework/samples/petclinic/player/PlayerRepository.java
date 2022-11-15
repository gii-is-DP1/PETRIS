package org.springframework.samples.petclinic.player;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.user.User;

public interface PlayerRepository extends CrudRepository<Player, Integer>{

    List<Player> findAll();

    @Query("SELECT p FROM Player p WHERE p.id = ?1")
    Player findPlayerByUserId(String userName);

    @Query("SELECT p FROM Player p WHERE p.user = ?1")
    List<Player> findAllPlayersFromAUser(String username);
    
}
