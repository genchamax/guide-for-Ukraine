package ua.com.guide.model;

import javax.persistence.*;

/**
 * Created by Max on 06.08.2016.
 */
@Entity
@Table (name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Integer imageId;

    @Column (name = "image_title", nullable = true)
    private String imageTitle;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
