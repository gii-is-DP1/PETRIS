package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PetrisBoardServiceTest {
    
    @Autowired
	protected PetrisBoardService petrisBoardService;

    @Autowired
	protected GameService gameService;

    
    @Test
    void shouldGetById(){
        Integer id = 1;
        PetrisBoard board = this.petrisBoardService.getById(id).get();
        assertThat(board.getHeight()).isEqualTo(1000);
    }
    @Test
    void shouldGetByGameId(){
        Integer id = 1;
        PetrisBoard board = this.petrisBoardService.getByGameId(id);
        assertThat(board.getHeight()).isEqualTo(1000);
    }
    @Test
    void shouldCreatePetrisBoard(){
        Game game = this.gameService.getAllPublicActiveEmptyGames().get(0);
        PetrisBoard board = this.petrisBoardService.createBoard(game);

        assertThat(board.getId()).isNotNull();
        assertThat(board.getGame().getId()).isEqualTo(game.getId());
        assertThat(board.getTokens().get(0).getId()).isNotEqualTo(0);
    }
}
