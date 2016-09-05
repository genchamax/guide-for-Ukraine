package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.guide.model.City;
import ua.com.guide.model.Region;
import ua.com.guide.model.enums.ImagePathLevel;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@Service
public class RegionService extends BasicService {

    @Autowired
    private ImageService imageService;

    public RegionService() {
        super(Region.class);
    }

    public List<City> getAllCityOfTheRegion(Integer id) {
        Region region = (Region) getById(id);
        return region.getCities();
    }

    public void deleteById(String root, Integer id) {
        imageService.deleteImagesInPath(root, ImagePathLevel.REGION, id);
        deleteById(id);
    }
}
