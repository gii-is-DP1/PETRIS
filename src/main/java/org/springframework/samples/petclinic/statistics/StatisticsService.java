package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private StatisticsRepository statisticsRepository;

    @Autowired
	public StatisticsService(StatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}

    public Statistics getStatisticByGameId(String gameId){
        return statisticsRepository.findStatisticByGameId(gameId);
    }

    public Statistics getStatisticById(String statisticId){
        return statisticsRepository.findStatisticById(statisticId);
    }
    
}
