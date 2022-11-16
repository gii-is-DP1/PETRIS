package org.springframework.samples.petclinic.friendRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/friendRequest")
    public String requestsList(ModelMap modelMap) {

        String vista = "requests/requestsList";
        UserDetails userDet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDet.getUsername()).get();
        List<FriendRequest> request = friendRequestRepository.findRequestByUser2(user);
        modelMap.addAttribute("request", request);

        return vista;
    }

    @PostMapping(path = "/friendRequest/{id}/accept")
    public String accept(@PathVariable("id") int id, ModelMap modelMap) {

        UserDetails userDet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDet.getUsername()).get();
        String start = friendRequestService.acceptRequest(id, user);
        return start;
    }
@PostMapping(path = "/friendRequest/{id}/decline")
    public String decline(@PathVariable("id") int id, ModelMap modelMap) {

        UserDetails userDet = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDet.getUsername()).get();
        FriendRequest request = friendRequestService.findById(id);
        String start = friendRequestService.declineRequest(request, user);
        return start;
    }

    @GetMapping(path = "/friendRequest/{username1}/{username2}/save")
    public String save(ModelMap modelMap, @PathVariable("username1") String username1, @PathVariable("username2") String username2) {

        User user1= userService.getUser(username1).get();
        User user2= userService.getUser(username2).get();
        if (!user1.getFriends().contains(user2)) {
            FriendRequest request = new FriendRequest();
            request.setUser1(user1);
            request.setUser2(user2);
            String start = friendRequestService.saveRequest(request);
            return start;

        } else {
            modelMap.addAttribute("message", "You are already friends");
            return "/error";
        }
    }

    @PostMapping(path = "/friendRequest/{user1}/{user2}/save")
    public String save(ModelMap modelMap, @PathVariable("user1") User user1, @PathVariable("user2") User user2) {

        if (!user1.getFriends().contains(user2)) {
            FriendRequest request = new FriendRequest();
            request.setUser1(user1);
            request.setUser2(user2);
            String back = friendRequestService.saveRequest(request);
            return back;

        } else {
            modelMap.addAttribute("message", "You are already friends");
            return "/error";
        }
    }

}