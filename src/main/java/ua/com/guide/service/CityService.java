package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.guide.model.City;
import ua.com.guide.model.Place;
import ua.com.guide.model.Region;
import ua.com.guide.model.enums.ImagePathLevel;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
public class CityService extends BasicService {

    public CityService() {
        super(City.class);
    }

    @Autowired
    private ImageService imageService;

    public Region getRegion(Integer id) {
        City city = (City) getById(id);
        return city.getRegion();
    }

    public List<Place> getAllPlacesOfTheCity(Integer cityId) {
        City city = (City) getById(cityId);
        return city.getPlaces();
    }

    public void deleteById(String root, Integer id) {
        imageService.deleteImagesInPath(root, ImagePathLevel.CITY, id);
        deleteById(id);
    }
}
