package org.springframework.samples.petclinic.chat;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends CrudRepository<Chat, Integer>{

    @Query("SELECT chat FROM Chat chat WHERE chat.game.id = ?1")
    Collection<Chat> findChatsById(@Param("id") Integer id);
    
}
