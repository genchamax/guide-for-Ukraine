package ua.com.guide.service;

import ua.com.guide.model.Image;

import java.io.File;

/**
 * Created by Max on 10.08.2016.
 */
public class ImageService extends BasicService {

    private static final String IMAGE_PATH = "src/main/webapp/static/images/";

    public ImageService() {
        super(Image.class);
    }

    public void saveImage(String regionName, String cityName, String placeName, String postId ){
        System.out.println(new File(IMAGE_PATH).getAbsolutePath());
    }

}
