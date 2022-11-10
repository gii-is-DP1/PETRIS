package org.springframework.samples.petclinic.token;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface TokenRepository extends Repository<Token, Integer>{

    @Query("SELECT tType FROM TokenType tType ORDER BY tType.name")
	List<TokenType> findTokenTypes() throws DataAccessException;

	Token findById(int id) throws DataAccessException;

    void save(Token token) throws DataAccessException;

}
