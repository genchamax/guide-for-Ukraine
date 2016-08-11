package ua.com.guide.service;

import ua.com.guide.model.Post;
import ua.com.guide.model.User;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
public class UserService extends BasicService {

    public UserService() {
        super(User.class);
    }

    public List<Post> getAllUserCreatedPost(Integer userId) {
        User user = (User) getById(userId);
        return user.getUserPosts();
    }

    public List<Post> getAllUserLikedPost(Integer userId) {
        User user = (User) getById(userId);
        return user.getLikedPosts();
    }
}
