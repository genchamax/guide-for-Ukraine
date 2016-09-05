package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import ua.com.guide.model.Image;
import ua.com.guide.service.ImageService;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Max on 12.08.2016.
 */
@Controller
@RequestMapping("/api/images")
public class ImageRestController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "region/{regionId}", method = RequestMethod.GET)
    private @ResponseBody List<String> getImagesPathOfRegionById(@PathVariable("regionId") Integer id) {
        return imageService.getImagesOfRegion(servletContext.getRealPath("/"), id);
    }

    @RequestMapping(value = "city/{cityId}", method = RequestMethod.GET)
    private @ResponseBody List<String> getImagesPathOfCityById(@PathVariable("cityId") Integer id) {
        return imageService.getImagesOfCity(servletContext.getRealPath("/"), id);
    }

    @RequestMapping(value = "place/{placeId}", method = RequestMethod.GET)
    private @ResponseBody List<String> getImagesPathOfPlaceById(@PathVariable("placeId") Integer id) {
        return imageService.getImagesOfPlace(servletContext.getRealPath("/"), id);
    }

    @RequestMapping(value = "post/{postId}", method = RequestMethod.GET)
    private @ResponseBody List<String> getImagesPathOfPostById(@PathVariable("postId") Integer id) {
        return imageService.getImagesOfPost(servletContext.getRealPath("/"), id);
    }


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

        imageService.saveImage(image, servletContext.getRealPath("/"));
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
