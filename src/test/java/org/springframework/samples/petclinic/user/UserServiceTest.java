package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
    
    @Autowired
	protected UserService userService;
    
    @Test
    void shouldFindUserByName(){
        User user = this.userService.getUserByName("gonriblun");
        assertThat(user.getPassword()).isEqualTo("1");
    }

    @Test
    void shouldFindUserByUserame(){
        Collection<User> user = this.userService.getUserByUsername("g");
        assertThat(user.size() >= 1);
    }

    @Test
    void shouldFindPlayerByUserame(){
        List<Player> players = this.userService.getPlayersByUser("gonriblun");
        assertThat(players.size() >= 1);
    }
    /*
     * @Test
    void shouldFindUser(){
        Optional<User> user = this.userService.getUser("gonriblun");
        assertThat(user.stream().anyMatch(user.get().getPassword() == "1"));
    }
     */

    @Test
	public void saveUserTest() throws DataAccessException, DuplicatedUserNameException {
		User u = new User();
		u.setUsername("jaimegg17");
		u.setEmail("jaimegg@gmail.com");
		u.setPassword("soyelmejor");
        userService.saveUser(u);

	}
    
}
