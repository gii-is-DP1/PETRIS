package org.springframework.samples.petclinic.friends;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {

    @Autowired
	private FriendRequestRepository friendRequestRepository;

    @Transactional
	public Iterable<FriendRequest> findAll() {
		return friendRequestRepository.findAll();
	}
    
    @Transactional
	public FriendRequest findById(Integer id) throws DataAccessException {
		return friendRequestRepository.findById(id).get();
	}

	@Transactional
	public String saveRequest(FriendRequest request) throws DataAccessException {
		if (request.getUser1() != request.getUser2()) {
			friendRequestRepository.save(request);
			return "/welcome";
		}
		return "/welcome";
	}

    @Transactional
	public String acceptRequest(int id, User userAutenticado) throws DataAccessException {

		FriendRequest request = this.findById(id);
		if (userAutenticado == request.getUser2()) {
			this.saveFriends(request.getUser1().getUsername(), request.getUser2().getUsername());
			this.declineRequest(request, userAutenticado);

		}
		return "redirect:/welcome";
	}

    @Transactional
	public String declineRequest(FriendRequest request, User userAutenticated) throws DataAccessException {
		if (request.getUser2() != request.getUser1()) {
			friendRequestRepository.delete(request);
		}
		return "redirect:/welcome";
	}

    @Transactional
	public void deleteUser(User user) throws DataAccessException {
		List<FriendRequest> requestUser1 = friendRequestRepository.findRequestByUser1(user);
		List<FriendRequest> requestUser2 = friendRequestRepository.findRequestByUser2(user);
		for (FriendRequest f1 : requestUser1) {
			friendRequestRepository.delete(f1);
		}
		for (FriendRequest f2 : requestUser2) {
			friendRequestRepository.delete(f2);
		}
	}

    @Transactional
	public void saveFriends(String username1, String username2) throws DataAccessException {
		if (username1 != username2) {
			friendRequestRepository.saveFriendUser1(username1, username2);
			friendRequestRepository.saveFriendUser2(username2, username1);
		} else {
			throw new DataAccessException("Un usuario no puede agregarse a si mismo") {
			};
		}
	}

}

