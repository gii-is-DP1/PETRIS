/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;

    @Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public Collection<User> getUserByUsername(String username) throws DataAccessException {
		return userRepository.findByUsername(username);
	}

	public List<Player> getPlayersByUser(String username) {
		return userRepository.findByName(username).getPlayers();
	}

	public Page<User> getAllRegisteredUsers(Pageable page){
		return userRepository.findAllUsers(page);
	}

	@Transactional
	public List<User> getBestPlayers(){
		return userRepository.findBestPlayer();
	}
	
	@Transactional
	public Optional<User> getUser(String username) {
		return userRepository.findById(username);
	}

	@Transactional(rollbackFor = {DataAccessException.class, DuplicatedUserNameException.class})
	public void saveUser(User user) throws DataAccessException, DuplicatedUserNameException{
		
			user.setEnabled(true);
			userRepository.save(user);
			authoritiesService.saveAuthorities(user.getUsername(), "user");
		
	}

	@Transactional
	public List<User> findAmigos(String user){
		return userRepository.findByName(user).getFriends();
	}

	@Transactional
	public void borrarAmigo(User user, String username) throws DataAccessException {

		List<User> friends = user.getFriends();
		User friend = userRepository.findByName(username);
		List<User> othersFriends = friend.getFriends();
		friends.remove(friend);
		othersFriends.remove(user);
		user.setFriends(friends);
		friend.setFriends(othersFriends);	
		userRepository.save(user);
		userRepository.save(friend);
	}

	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Transactional
    public List<Object> auditoria() {
        return userRepository.auditoria();
    }
}
