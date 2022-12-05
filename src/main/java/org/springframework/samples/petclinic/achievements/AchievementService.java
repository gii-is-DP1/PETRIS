package org.springframework.samples.petclinic.achievements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

    AchievementRepository achievementRepository;

    @Autowired
	public AchievementService(AchievementRepository achievementRepository) {
		this.achievementRepository = achievementRepository;
	}

    public List<Achievement> getAllAchievements(){
        return achievementRepository.findAll();
    }

    public Achievement getAchievementById(Integer id){
        return achievementRepository.findAchievementByid(id);        
    }

    public Achievement save(Achievement a){
        return achievementRepository.save(a);
    }
    
}
