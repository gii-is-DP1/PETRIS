package org.springframework.samples.petclinic.token;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Integer>{

    @Query("SELECT tType FROM TokenType tType ORDER BY tType.name")
	List<TokenType> findTokenTypes() throws DataAccessException;

	Token findById(int id) throws DataAccessException;

	@Query("SELECT t FROM Token t WHERE t.space.id = ?1 AND t.colour.name = ?2")
	List<Token> findTokensOfSpace(Integer spaceId, String colour);

	@Query("SELECT t FROM Token t WHERE t.space.id = ?1 AND t.colour.name = ?2 AND t.hasBeenUsed = false ORDER BY position_in_space DESC")
    List<Token> findPossibleTokensToChangeOfSpace(Integer spaceId, String colour);

	@Query("SELECT t FROM Token t WHERE t.petrisBoard.id = ?1 AND t.colour.name = ?2 AND t.tokenType.name = ?3 AND t.space = null")
    List<Token> findTokensToAddInBinaryFision(Integer petrisBoardId, String colour, String tokenType);

	@Query("SELECT t FROM Token t WHERE t.space.id = ?1")	
	List<Token> findTokensToQuit(Integer spaceId);

	@Query("SELECT t FROM Token t WHERE t.petrisBoard.id = ?1")
    List<Token> findTokensByPetrisBoardId(Integer petrisBoardId);


}
