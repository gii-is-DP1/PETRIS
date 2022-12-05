package org.springframework.samples.petclinic.token;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TokenTypeRepository extends CrudRepository<TokenType, Integer>{

    @Query("SELECT t FROM TokenType t WHERE t.name = ?1")
    TokenType findTokenTypeByName(String name);
    
}
