package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.Post;
import ua.com.guide.model.User;
import ua.com.guide.service.UserService;

import java.util.List;

/**
 * Created by Max on 11.08.2016.
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Integer userId) {
        return (User) userService.getById(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable("id") Integer userId) {
        userService.deleteById(userId);
        return "redirect:";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        userService.create(user);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String changeUser(@PathVariable("id") Integer userId, @RequestBody User user) {
        user.setUserId(userId);
        userService.update(user);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}/posts/created", method = RequestMethod.GET)
    public List<Post> getCreatedPosts(@PathVariable("id") Integer userId) {
        return userService.getAllUserCreatedPost(userId);
    }

    @RequestMapping(value = "/{id}/posts/liked", method = RequestMethod.GET)
    public List<Post> getLikedPost(@PathVariable("id") Integer userId) {
        return userService.getAllUserLikedPost(userId);
    }
}
