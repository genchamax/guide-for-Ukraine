package ua.com.guide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties(allowSetters = true)
    private String password;

    @Column(name = "USER_LOGIN", nullable = false)
    @JsonIgnoreProperties(allowSetters = true)
    private String login;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_SURNAME")
    private String userSurname;

    @Column(name = "USER_PATRONYMIC")
    private String userPatronymic;

    @Column(name = "USER_BIRTH")
    private Date userBirth;

    // Default value false (in database)
    @Column(name = "ENABLED")
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnore
    private List<Post> userPosts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_FAVOURITE_POST",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<Post> likedPosts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

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

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + userName.hashCode() + userBirth.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;

        User aUser = (User) obj;

        return aUser.hashCode() == this.hashCode() && this.userId.equals(aUser.getUserId());

    }
}
