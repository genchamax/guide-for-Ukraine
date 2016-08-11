package ua.com.guide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Max on 06.08.2016.
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer userId;

    @Column(name = "USER_PASSWORD", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "USER_LOGIN", nullable = false)
    @JsonIgnore
    private String login;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_SURNAME")
    private String userSurname;

    @Column(name = "USER_PATRONYMIC")
    private String userPatronymic;

    @Column(name = "USER_BIRTH")
    private Date userBirth;

    @Column(name = "USER_STATUS", nullable = false)
    private String userStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Post> userPosts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_FAVOURITE_POST",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<Post> likedPosts;

    @OneToMany
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
//    @JoinColumn(name = "FAVOURITE_POSTS_ID")
//    private List<Post> favouritePosts;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPatronymic() {
        return userPatronymic;
    }

    public void setUserPatronymic(String userPatronymic) {
        this.userPatronymic = userPatronymic;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

//    public List<Post> getFavouritePosts() {
//        return favouritePosts;
//    }
//
//    public void setFavouritePosts(List<Post> favouritePosts) {
//        this.favouritePosts = favouritePosts;
//    }
}
