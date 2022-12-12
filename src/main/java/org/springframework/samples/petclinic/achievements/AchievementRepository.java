package org.springframework.samples.petclinic.achievements;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
    
    List<Achievement> findAll();

    public Achievement findByName(String name);
    
    @Query("SELECT a FROM Achievement a WHERE a.id = ?1")
    Achievement findAchievementByid(Integer id);

    @Query("SELECT u.achievements FROM User u WHERE u.id=:username")
    public List<Achievement> findPlayerAchievements(@Param("username") String username);

    

}
