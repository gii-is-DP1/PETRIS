package org.springframework.samples.petclinic.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.Colour.Colour;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.space.Space;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {

    @Autowired
	protected GameService gameService;

    @Autowired
    protected UserService userService;
    
    @Test
	void shouldFindAllGames() {
		List<Game> games = this.gameService.getAllGames();
		assertThat(games.size()).isEqualTo(10);
	}

    /*
     
    @Test
    void shouldFindAllPlayingGames(){
        List<Game> playingGames = this.gameService.getAllPlayingGames();
        assertThat(playingGames.size()).isEqualTo(5);
    }
    */

    @Test
    void shouldFindActiveEmptyGames(){
        List<Game> activeGames = this.gameService.getAllPublicActiveEmptyGames();
        assertThat(activeGames.size()).isEqualTo(3);
    }

    @Test
    void shouldFindFinishedGames(){
        List<Game> finishedGames = this.gameService.getAllFinishedGames();
        assertThat(finishedGames.size()).isEqualTo(2);
    }

    @Test
	void shouldFindGameByPlayerId() {
		Game game = this.gameService.getGameByPlayerId(1);
		assertThat(game.getWinner()).isEqualTo("B");

	}
    @Test
	void shouldGenerateCode() {
		String code = this.gameService.generateCode();
        assertThat(code.length()).isEqualTo(5);

	}
    @Test
	void shouldFindGameById() {
		Game game = this.gameService.getGameById(1);
		assertThat(game.getId()).isEqualTo(1);
        assertThat(game.getTime()).isEqualTo(15);

	}
    @Test
	void shouldFindAllPublicActiveEmptyGames() {
		List<Game> games = this.gameService.getAllPublicActiveEmptyGames();
		assertThat(games.size()).isEqualTo(3);

	}
    @Test
    void shouldSaveInRepository(){
        Integer numGames = this.gameService.getAllGames().size();
        Game newGame = new Game();

        newGame.setId(6);
        newGame.setTime(-5);
        assertThrows( ConstraintViolationException.class,() -> gameService.save(newGame), "You are not constraining time to positive values");
        newGame.setTime(35);
        
        newGame.setRound(4);

        this.gameService.save(newGame);
        assertThat(this.gameService.getAllGames().size()).isEqualTo(numGames +1);
        assertThat(newGame.getId()).isNotEqualTo(0);

    }

    @Test
    void permittedNumToMoveTest(){
        Space space1 = Space.createSpace(1,4,2,0,0);
        Space space2 = Space.createSpace(2,3,4,0,0);
        Space space3 = Space.createSpace(1,0,1,0,0);
        Space space4 = Space.createSpace(2,0,0,0,0);

        
        boolean move1 = this.gameService.permittedNumToMove(space1, space2, 6, "black");
        assertThat(move1).isEqualTo(false);

        boolean move2 = this.gameService.permittedNumToMove(space1, space2, 3, "black");
        assertThat(move2).isEqualTo(false);

        boolean move3 = this.gameService.permittedNumToMove(space1, space2, 1, "black");
        assertThat(move3).isEqualTo(false);

        boolean move4 = this.gameService.permittedNumToMove(space1, space2, 2, "black");
        assertThat(move4).isEqualTo(false);

        boolean move5 = this.gameService.permittedNumToMove(space3, space4, 1, "red");
        assertThat(move5).isEqualTo(true);
    }
    @Test
    void isNeighbourTest(){
        Space space1 = Space.createSpace(1,0,0,0,0);
        Space space2 = Space.createSpace(6,0,0,0,0);
        Space space3 = Space.createSpace(5,0,0,0,0);
        Space space4 = Space.createSpace(7,0,0,0,0);


        boolean move1 = this.gameService.isNeighbour(space1, space2);
        assertThat(move1).isEqualTo(true);

        boolean move2 = this.gameService.isNeighbour(space1, space3);
        assertThat(move2).isEqualTo(false);

        boolean move3 = this.gameService.isNeighbour(space4, space3);
        assertThat(move3).isEqualTo(true);

        boolean move4 = this.gameService.isNeighbour(space1, space1);
        assertThat(move4).isEqualTo(false);
    }
    @Test
    void moveToSpaceWithoutSarcineTest(){
        Space space1 = Space.createSpace(1,0,1,1,0);
        Space space2 = Space.createSpace(1,0,1,0,0);

        boolean move1 = this.gameService.moveToSpaceWithoutSarcine("black",space1);
        assertThat(move1).isEqualTo(false);

        boolean move2 = this.gameService.moveToSpaceWithoutSarcine("black",space2);
        assertThat(move2).isEqualTo(true);
    }
    @Test
    void isMovementAllowedTest(){
        Colour colour = new Colour();
        colour.setName("red");

        Player player = new Player();
        player.setColour(colour);
        player.setTurn(true);

        Space space1 = Space.createSpace(1,0,3,1,0);
        Space space2 = Space.createSpace(4,0,1,0,0);
        Space space3 = Space.createSpace(2,0,0,0,1);
        Space space4 = Space.createSpace(7,1,0,0,0);
        Space space5 = Space.createSpace(1,0,1,0,0);
        Space space6 = Space.createSpace(2,0,0,0,0);
        Space space7 = Space.createSpace(7,1,4,0,0);
        Space space8 = Space.createSpace(2,0,0,1,0);

        //debe dar falso por position
        boolean move1 = this.gameService.isMovementAllowed(player, space1, space2,2);
        assertThat(move1).isEqualTo(false);
        //debe dar falso por tener sarcina
        boolean move2 = this.gameService.isMovementAllowed(player, space1, space3,1);
        assertThat(move2).isEqualTo(false);
        //debe dar falso por numero invalido de numBacteriaToMove
        boolean move3 = this.gameService.isMovementAllowed(player, space1, space4,1);
        assertThat(move3).isEqualTo(false);

        boolean move4 = this.gameService.isMovementAllowed(player, space1, space4,2);
        assertThat(move4).isEqualTo(true);
    
        boolean move5 = this.gameService.isMovementAllowed(player, space5, space6, 1);
        assertThat(move5).isEqualTo(true);

        boolean move6 = this.gameService.isMovementAllowed(player, space7, space8, 4);
        assertThat(move6).isEqualTo(true);
    }

}
