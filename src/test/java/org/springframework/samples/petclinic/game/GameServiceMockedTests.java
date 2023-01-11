package org.springframework.samples.petclinic.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


/* 

@WebMvcTest(controllers = GameController.class)
public class GameServiceMockedTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;


    @BeforeEach
    void setup() {

        

        Game g1 = new Game();
        g1.setActive(false);
        g1.setId(0);
        g1.setPublic(false);

        Game g2 = new Game();
        g1.setActive(true);
        g1.setId(1);
        g1.setPublic(false);

        Game g3 = new Game();
        g1.setActive(true);
        g1.setId(2);
        g1.setPublic(true);

        Game g4 = new Game();
        g1.setActive(false);
        g1.setId(3);
        g1.setPublic(false);

        Game g5 = new Game();
        g1.setActive(false);
        g1.setId(4);
        g1.setPublic(false);

        Game g6 = new Game();
        g1.setActive(true);
        g1.setId(5);
        g1.setPublic(true);

        Game g7 = new Game();
        g1.setActive(true);
        g1.setId(6);
        g1.setPublic(false);

        Game g8 = new Game();
        g1.setActive(false);
        g1.setId(7);
        g1.setPublic(false);

        List<Game> gs = new ArrayList<>();
        gs.add(g8);

        when(gameService.getAllGames()).thenReturn(gs);
        
    }

    @Test
    public void sgame(){
        assertThat(gameService.getAllGames()).isEqualTo(1000);
    }


    

    
    //perform(get("/owners/new")).andExpect(status().isOk());
    
}
*/
