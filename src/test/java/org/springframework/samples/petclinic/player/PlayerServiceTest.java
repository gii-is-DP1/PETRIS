package org.springframework.samples.petclinic.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerServiceTest {
    
    @Autowired
	protected PlayerService playerService;

	@Test
	void shouldListAllPlayer(){
		List<Player> players = this.playerService.getAll();
		assertThat(players.size()).isEqualTo(8);
	}
    @Test
	void shouldFindPlayersByUser() {
        
		List<Player> players = this.playerService.getPlayersByUser("raumerbas");
		assertThat(players.size()).isEqualTo(4);
	}
	@Test
	void shouldFindPlayersOfGame() {
        
		List<Player> players = this.playerService.getPlayersOfGame(1);
		assertThat(players.size()).isEqualTo(2);
	}
}
