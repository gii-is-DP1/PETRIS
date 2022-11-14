package org.springframework.samples.petclinic.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends  CrudRepository<User, String>{

    @Query("SELECT  p from User p ")
	Iterable<User> getUsers();

    @Query("SELECT  p from User p WHERE p.username = :username ")
	Iterable<User> getUserByUsername(String username);
	
    @Query("SELECT user FROM User user WHERE user.username LIKE :username%")
	public User findByName(@Param("username") String username);

}
