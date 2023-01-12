package org.springframework.samples.petclinic.game;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;




@WebMvcTest(controllers = GameController.class, 
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class GameServiceMockedTests {

    @Autowired
    private GameController gameController;

    @MockBean
    private GameService gameService;

    @MockBean
    private AuthoritiesService authoritiesService;

    @Autowired
    private MockMvc mockMvc;

    private Game g1;


    @BeforeEach
    void setup() {

        

        Game g1 = new Game();
        g1.setActive(false);
        g1.setId(0);
        g1.setPublic(false);
        g1.setWinner("red");
        given(this.gameService.getGameById(0)).willReturn(g1);

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
        gs.add(g1);
        given(this.gameService.getAllFinishedGames()).willReturn(gs);

        
    }

    /*
     
    @WithMockUser(value = "spring")
	@Test
	public void testInitFindForm() throws Exception {
        mockMvc.perform(get("/games/finished")).andExpect(status().isOk())
        .andExpect(model().attributeExists("games"))
        .andExpect(view().name("games/finished"));
	}

    @WithMockUser(value = "spring")
	@Test
	public void testProcessFindFormSuccess() throws Exception {
        given(this.gameService.getAllFinishedGames()).willReturn(Lists.newArrayList(g1, new Game()));
        
		mockMvc.perform(get("/games/finished")).andExpect(status().isOk()).andExpect(view().name("games/finishedGames"));
	}
    */



    

    
    //perform(get("/owners/new")).andExpect(status().isOk());
    
}

