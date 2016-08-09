package ua.com.guide.model;

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

//    @OneToMany(cascade = CascadeType.ALL, /*TODO What do that params*/mappedBy = "region", fetch = FetchType.LAZY)
//    @JoinColumn(name = "IMAGE_ID")
//    private List<Image> images;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "PLACE_ID", nullable = false)
//    private Place place;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID", nullable = false)
//    private User user;

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

//    public List<Image> getImages() {
//        return images;
//    }
//
//    public void setImages(List<Image> images) {
//        this.images = images;
//    }
//
//    public Place getPlace() {
//        return place;
//    }
//
//    public void setPlace(Place place) {
//        this.place = place;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
