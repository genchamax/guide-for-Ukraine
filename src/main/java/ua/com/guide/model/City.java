package ua.com.guide.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Max on 06.08.2016.
 */
@Entity
@Table(name = "CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer cityId;

    @Column(name = "CITY_DESCRIPTION")
    private String cityDescription;

    @Column(name = "CITY_NAME", nullable = false)
    private String cityName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "REGION_ID", nullable = false)
    private Region region;
//    @OneToMany(cascade = CascadeType.ALL, /*TODO What do that params*/mappedBy = "region", fetch = FetchType.LAZY)
//    @JoinColumn(name = "IMAGE_ID")
//    private List<Image> images;

//    @ManyToOne(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY)
//    @JoinColumn(name = "REGION_ID", nullable = false)
//    private Region region;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

//    public List<Image> getImages() {
//        return images;
//    }
//
//    public void setImages(List<Image> images) {
//        this.images = images;
//    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
