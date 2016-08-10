package ua.com.guide.service;

import org.springframework.stereotype.Service;
import ua.com.guide.model.City;
import ua.com.guide.model.Region;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@Service
public class RegionService extends BasicService {

    public RegionService() {
        super(Region.class);
    }

    public List<City> getAllCityOfTheRegion(Integer id) {
        Region region = (Region) getById(id);
        return region.getCities();
    }
}
