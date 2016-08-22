package ua.com.guide.model;

import javax.persistence.*;

/**
 * Created by Max on 06.08.2016.
 */
@Entity
@Table(name = "PLACE")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO: 06.08.2016 Learn difference
    @Column(name = "ID", nullable = false)
    private Integer placeId;

    @Column(name = "PLACE_NAME", nullable = false)
    private String placeName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
