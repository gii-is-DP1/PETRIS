package org.springframework.samples.petclinic.achievements;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.DuplicatedUserNameException;

import java.util.List;

import org.junit.jupiter.api.Test;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementServiceTest {
    @Autowired
	protected AchievementService achievementService;

    @Test
	public void saveAchievementTest() throws DataAccessException, DuplicatedUserNameException {
		Achievement a = new Achievement();
		a.setName("Eres todo un maquinote");
		a.setDescription("Has conseguido ser muuuy bueno");
        a.setThreshold(3.0);
        a.setMetric(Metric.VICTORIES);
        achievementService.save(a);
	}

    @Test
    void shouldFindAchievements(){
        List<Achievement> achievements = this.achievementService.getAllAchievements();
        assertThat(achievements.size()).isEqualTo(2);
    }

    @Test
    void shouldFindAchievementById(){
        Achievement achievement = this.achievementService.getAchievementById(1);
        assertThat(achievement.getName()).isEqualTo("Petriplayer");
    }
    
    @Test
    void shouldFindAchievementByName(){
        Achievement achievement = this.achievementService.getAchievementByName("Proplayer");
        assertThat(achievement.getId()).isEqualTo(2);
    }

    @Test
    void shouldDeleteAchievement(){
        Achievement achievement = this.achievementService.getAchievementByName("Petriplayer");

        List<Achievement> achievements = this.achievementService.getAllAchievements();
        achievements.remove(achievement);
        assertThat(achievements.size()).isEqualTo(1);
    }

    @Test
    void shouldFindAchievementByUser(){
        List<Achievement> achievements = this.achievementService.getAchievementsByUser("raumerbas");
        assertThat(achievements.size()).isEqualTo(0);
    }

}
