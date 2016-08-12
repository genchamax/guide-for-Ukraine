package ua.com.guide.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Max on 06.08.2016.
 */
@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer postId;

    @Column(name = "POST_NAME", nullable = false)
    private String postName;

    @Column(name = "POST_BODY", nullable = false)
    private String postBody;

    @Column(name = "PUBLISH_DATE", nullable = false)
    private Date publishDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "PLACE_ID", nullable = false)
    private Place place;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_FAVOURITE_POST",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "POST_ID"))
    @JsonIgnore
    private List<User> likingUser; // TODO: 11.08.2016 Check grammar

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getLikingUser() {
        return likingUser;
    }

    public void setLikingUser(List<User> likingUser) {
        this.likingUser = likingUser;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
