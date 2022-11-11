package org.springframework.samples.petclinic.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{

    @Query("SELECT  p from User p ")
	Iterable<User> getUsers();

    @Query("SELECT  p from User p WHERE p.username = :username ")
	Iterable<User> getUserByUsername(String username);

    
	
}
