package org.springframework.samples.petclinic.Colour;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ColourRepository extends CrudRepository<Colour,Integer>{
    
    List<Colour> findAll();

    @Query("SELECT c FROM Colour c WHERE c.name = ?1")
    Colour findColourByName(String name);

    @Query("SELECT c FROM Colour c WHERE c.name != ?1")
    List<Colour> findOtherColoursExcept(String name);
}
