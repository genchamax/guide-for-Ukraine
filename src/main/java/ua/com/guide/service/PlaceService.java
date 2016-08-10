package ua.com.guide.service;

import ua.com.guide.model.City;
import ua.com.guide.model.Place;

/**
 * Created by Max on 10.08.2016.
 */
public class PlaceService extends BasicService {

    public PlaceService() {
        super(Place.class);
    }

    public City getCity(Integer placeId) {
        Place place = (Place) getById(placeId);
        return place.getCity();
    }
}
