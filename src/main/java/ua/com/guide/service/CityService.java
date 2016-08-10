package ua.com.guide.service;

import ua.com.guide.model.City;
import ua.com.guide.model.Region;

/**
 * Created by Max on 10.08.2016.
 */
public class CityService extends BasicService {

    public CityService() {
        super(City.class);
    }

    public Region getRegion(Integer id) {
        City city = (City) getById(id);
        return city.getRegion();
    }
}
