package org.springframework.samples.petclinic.user;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends  CrudRepository<User, String>{

    @Query("SELECT  p from User p ")
	Iterable<User> findUsers();
	
    @Query("SELECT user FROM User user WHERE user.username = ?1")
	 User findByName(String username);

    @Query("SELECT DISTINCT user FROM User user WHERE user.username LIKE :username%")
	public Collection<User> findByUsername(@Param("username") String username);

    //@Query("SELECT user FROM User user ORDER BY user.points DESC LIMIT 3")
    @Query( value = "SELECT * FROM USERS ORDER BY points DESC LIMIT 3" , nativeQuery = true)
    public List<User> findBestPlayer();

    @Query(value = "SELECT * FROM USERS", nativeQuery = true)
    public Page<User> findAllUsers(Pageable pageable);

}
