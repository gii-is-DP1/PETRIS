package org.springframework.samples.petclinic.chat;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    @Autowired 
    ChatRepository chatRepository;

    @Autowired
	public ChatService(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

    @Transactional
    public void saveChat(Chat chat) {
       chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
	public Collection<Chat> getChatsById(Integer id) throws DataAccessException {
		return chatRepository.findChatsById(id);
	}
    
}
