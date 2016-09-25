package ua.com.guide.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public User create(User user) {
        user.setPassword(encryptUserPassword(user));
        return (User) super.create(user);
    }

    public User update(User user) {
        user.setPassword(encryptUserPassword(user));
        return (User) super.update(user);
    }

    public List<Post> getAllUserCreatedPost(Integer userId) {
        User user = (User) getById(userId);
        return user.getUserPosts();
    }

    public List<Post> getAllUserLikedPost(Integer userId) {
        User user = (User) getById(userId);
        return user.getLikedPosts();
    }

    private String encryptUserPassword(User user) {
        String userPassword = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(userPassword);
    }
}
