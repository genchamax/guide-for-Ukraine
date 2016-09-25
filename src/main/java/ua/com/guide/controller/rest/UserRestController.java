package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.Post;
import ua.com.guide.model.UserDetails;
import ua.com.guide.service.UserService;

import java.util.List;

/**
 * Created by Max on 11.08.2016.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<UserDetails> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDetails getUserById(@PathVariable("id") Integer userId) {
        return (UserDetails) userService.getById(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") Integer userId) {
        userService.deleteById(userId);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public void addUser(@RequestBody UserDetails user) {
        userService.create(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editUser(@PathVariable("id") Integer userId, @RequestBody UserDetails user) {
        user.setUserId(userId);
        userService.update(user);
    }

    @RequestMapping(value = "/{id}/posts/created", method = RequestMethod.GET)
    public List<Post> getCreatedPosts(@PathVariable("id") Integer userId) {
        return userService.getAllUserCreatedPost(userId);
    }

    @RequestMapping(value = "/{id}/posts/liked", method = RequestMethod.GET)
    public List<Post> getLikedPost(@PathVariable("id") Integer userId) {
        return userService.getAllUserLikedPost(userId);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public UserDetails getCurrentUser () {
        return userService.getCurrentUser();
    }
}
