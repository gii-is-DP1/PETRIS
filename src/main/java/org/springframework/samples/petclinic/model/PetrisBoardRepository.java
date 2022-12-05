package org.springframework.samples.petclinic.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PetrisBoardRepository extends  CrudRepository<PetrisBoard, Integer> {

    @Query("SELECT p FROM PetrisBoard p WHERE p.game.id = ?1")
    PetrisBoard findByGameId(Integer id);
    
}
