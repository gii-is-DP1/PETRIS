package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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
        User user = this.userService.getUser("gonriblun").get();
        assertThat(user.getPassword()).isEqualTo("1");
    }

    @Test
	void shouldFindUsersByLetter() {
		Collection<User> users = this.userService.getUserByUsername("p");
		assertThat(users.size()).isEqualTo(2);

		users = this.userService.getUserByUsername("hola");
		assertThat(users.isEmpty()).isTrue();
	}

    @Test
    void shouldFindPlayerByUserame(){
        List<Player> players = this.userService.getPlayersByUser("gonriblun");
        assertThat(players.size()).isEqualTo(3);
    }

    @Test
	public void saveUserTest() throws DataAccessException, DuplicatedUserNameException {
		User u = new User();
		u.setUsername("jaimegg17");
		u.setEmail("jaimegg@gmail.com");
		u.setPassword("soyelmejor");
        userService.saveUser(u);

	}

    @Test
    void shouldFindFriends(){
        List<User> friends = this.userService.findAmigos("raumerbas");
        assertThat(friends.size()).isEqualTo(2);

        friends = this.userService.findAmigos("lucantdel");
        assertThat(friends.isEmpty());
    }

    @Test
    void shouldDeleteFriend(){
        User friend = this.userService.getUser("dancorfon").get();

        List<User> friends = this.userService.findAmigos("raumerbas");
        friends.remove(friend);
        assertThat(friends.size()).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionSavingUserWithShortPassword(){
        User u = new User();
		u.setUsername("jaimegg17");
		u.setEmail("jaimegg@gmail.com");
		u.setPassword("a");

        Assertions.assertThrows(Exception.class, () ->{
			userService.saveUser(u);
		});
    }

    @Test
    void shouldThrowExceptionSavingUserWithWrongEmail(){
        User u = new User();
		u.setUsername("jaimegg17");
		u.setEmail("jaimegg");
		u.setPassword("aaaaa");

        Assertions.assertThrows(Exception.class, () ->{
			userService.saveUser(u);
		});
    }

    
    
}
