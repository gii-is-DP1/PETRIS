package org.springframework.samples.petclinic.statistics;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer> {

    List<Statistics> findAll();

    @Query("SELECT s FROM Statistics s WHERE s.id = ?1")
    Statistics findStatisticById(String statisticsId);

    @Query("SELECT s FROM Statistics s WHERE s.game.id = ?1")
    Statistics findStatisticByGameId(String gameId);
    
}
