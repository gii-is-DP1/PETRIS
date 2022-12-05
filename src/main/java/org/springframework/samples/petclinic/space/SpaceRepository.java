package org.springframework.samples.petclinic.space;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SpaceRepository  extends CrudRepository<Space,Integer>{
    

    @Query("SELECT s FROM Space s WHERE s.id = ?1")
    Space findSpaceByid(Integer id);
}
