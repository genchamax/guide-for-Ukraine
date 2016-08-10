package ua.com.guide.service;

import org.springframework.stereotype.Service;
import ua.com.guide.model.Region;

/**
 * Created by Max on 10.08.2016.
 */
@Service
public class RegionService extends BasicService {

    public RegionService() {
        super(Region.class);
    }

}
