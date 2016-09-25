package ua.com.guide.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.guide.model.Post;
import ua.com.guide.model.UserDetails;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@Service
public class UserService extends BasicService {

    public UserService() {
        super(UserDetails.class);
    }

    @Autowired
    SessionFactory sessionFactory;

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
    public UserDetails create(UserDetails user) {
        user.setPassword(encryptUserPassword(user));
        return (UserDetails) super.create(user);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public UserDetails update(UserDetails user) {
        user.setPassword(encryptUserPassword(user));
        return (UserDetails) super.update(user);
    }

    public List<Post> getAllUserCreatedPost(Integer userId) {
        UserDetails user = (UserDetails) getById(userId);
        return user.getUserPosts();
    }

    public List<Post> getAllUserLikedPost(Integer userId) {
        UserDetails user = (UserDetails) getById(userId);
        return user.getLikedPosts();
    }

    private String encryptUserPassword(UserDetails user) {
        String userPassword = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(userPassword);
    }

    @PreAuthorize("authenticated")
    public UserDetails getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return (UserDetails) sessionFactory.getCurrentSession().createQuery(
                "FROM UserDetails WHERE email = :email")
                .setParameter("email", user.getUsername())
                .uniqueResult();
    }
}
