package ua.com.guide.service;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public List getAll() {
        return super.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public Object getById(Integer id) {
        return super.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public void delete(Object entity) {
        super.delete(entity);
    }

    @PreAuthorize("permitAll()")
    public User create(User user) {
        user.setPassword(encryptUserPassword(user));
        return (User) super.create(user);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
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
