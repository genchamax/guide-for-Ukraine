package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import ua.com.guide.model.Image;
import ua.com.guide.service.ImageService;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Max on 12.08.2016.
 */
@Controller
@RequestMapping("/api/images")
public class ImageRestController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public void addImage(@RequestParam("file") MultipartFile file,
                         @RequestParam("regionId") Integer regionId,
                         @RequestParam(value = "cityId", required = false) Integer cityId,
                         @RequestParam(value = "placeId", required = false) Integer placeId,
                         @RequestParam(value = "postId", required = false) Integer postId) {

        Image image = new Image();
        image.setRegionId(regionId);
        image.setCityId(cityId);
        image.setPlaceId(placeId);
        image.setPostId(postId);
        image.setImageFile(file);

        String imagesPath = servletContext.getRealPath("/") + "static/images/";

        imageService.saveImage(image, imagesPath);
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
