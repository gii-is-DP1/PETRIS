package org.springframework.samples.petclinic.friends;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer>{

    @Query("SELECT i FROM FriendRequest i WHERE i.user1 = :user")
	public List<FriendRequest> findRequestByUser1(@Param("user") User user);

	@Query("SELECT i FROM FriendRequest i WHERE i.user2 = :user")
	public List<FriendRequest> findRequestByUser2(@Param("user") User user);

	@Modifying
	@Query(value = "INSERT INTO FRIENDS(user_username, friends_username) VALUES (:username1,:username2) AND INSERT INTO FRIENDS(user_username, friends_username) VALUES (:username2,:username1)", nativeQuery = true)
	public void saveFriends(@Param("username1") String username1, @Param("username2") String username2);

	@Modifying
	@Query(value = "INSERT INTO FRIENDS(user_username, friends_username) VALUES (:username1,:username2)", nativeQuery = true)
	public void saveFriendUser1(@Param("username1") String username1, @Param("username2") String username2);

	@Modifying
	@Query(value = "INSERT INTO FRIENDS(user_username, friends_username) VALUES (:username2,:username1)", nativeQuery = true)
	public void saveFriendUser2(@Param("username2") String username2, @Param("username1") String username1);

	@Query(value = "SELECT f.friends_username FROM FRIENDS f WHERE f.user_username = :username", nativeQuery = true)
	public List<String> findFriendsUserFromUsername(@Param("username") String username);
    
}
