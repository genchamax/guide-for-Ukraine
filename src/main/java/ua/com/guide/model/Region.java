package ua.com.guide.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Max on 06.08.2016.
 */
@Entity
@Table(name = "REGION")
public class Region {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer regionId;

    @Column(name = "REGION_NAME", nullable = false)
    private String regionName;

//    @OneToMany(cascade = CascadeType.ALL, /*TODO What do that params*/mappedBy = "region", fetch = FetchType.LAZY)
//    @JoinColumn(name = "IMAGE_ID")
//    private List<Image> images;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

//    public List<Image> getImages() {
//        return images;
//    }
//
//    public void setImages(List<Image> images) {
//        this.images = images;
//    }
}
