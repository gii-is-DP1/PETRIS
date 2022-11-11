package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Integer>{

    List<Game> findAll();

    @Query("SELECT g FROM Game g WHERE g.id = ?1")
    Game findGameByid(Integer id);

    @Query("SELECT g FROM Game g WHERE g.isActive = true")
    List<Game> findAllActiveGames();
    
}
