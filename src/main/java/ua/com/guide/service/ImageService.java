package ua.com.guide.service;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Max on 10.08.2016.
 */
public class ImageService {

    private static final String IMAGE_PATH = "src/main/webapp/static/images/";

    public void saveImage(String regionName, String cityName, String placeName, Integer postId ){
        Path regionPath = Paths.get("../");
        Path cityPath = Paths.get(cityName);
        Path placePath = Paths.get(placeName);
        Path postPath = Paths.get("post" + postId);

        System.out.println(regionPath);
    }

    public static void main(String[] args) {
        ImageService imageService = new ImageService();
        imageService.saveImage("adfds","adfds","adfds", 5);
    }

}
