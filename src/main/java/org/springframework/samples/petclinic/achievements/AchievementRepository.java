/*
package org.springframework.samples.petclinic.achievements;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer> {

    List<Achievement> findAll();
    /*
    @Query("SELECT u FROM User u WHERE u.userId=:userId")
    public List<Achievement> findPlayerAchievements(String userId);
    */
    public Achievement findByName(String name);
    
}
*/