package org.springframework.samples.petclinic.chat;

import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Integer>{
    
}
