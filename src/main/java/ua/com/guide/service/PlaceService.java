package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.guide.model.City;
import ua.com.guide.model.Place;
import ua.com.guide.util.enums.ImagePathLevel;

/**
 * Created by Max on 10.08.2016.
 */
public class PlaceService extends BasicService {

    public PlaceService() {
        super(Place.class);
    }

    @Autowired
    private ImageService imageService;

    public City getCity(Integer placeId) {
        Place place = (Place) getById(placeId);
        return place.getCity();
    }

    public void deleteById(String root, Integer id) {
        imageService.deleteImagesInPath(root, ImagePathLevel.PLACE, id);
        deleteById(id);
    }
}
