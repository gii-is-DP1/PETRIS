package org.springframework.samples.petclinic.model;

import org.springframework.data.repository.CrudRepository;

public interface PetrisBoardRepository extends  CrudRepository<PetrisBoard, Integer> {
    
}
