package org.springframework.samples.petclinic.chat;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.stereotype.Service;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.user.DuplicatedUserNameException;
import org.springframework.samples.petclinic.user.User;






@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ChatServiceTest {

    @Autowired
    protected ChatService chatService;

    @Test
    void shouldFindChatById() {
        Integer id = 1;
        List<Chat> chats = this.chatService.getChatsById(id).stream().toList();
        assertThat(chats.get(0).getText()).isEqualTo("Aprende a jugar fenómeno, jajajajjaja. Te faltan bacterias");
    }

    @Test
	public void saveChatTest() throws DataAccessException, DuplicatedUserNameException {
		Chat c = new Chat();
        User u = new User();
        Game g = new Game();
		c.setUser(u);
		c.setGame(g);
		c.setText("soyelmejor");
        chatService.saveChat(c);

	}
    
}
