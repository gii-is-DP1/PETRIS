package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
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
    
}
