package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.Image;
import ua.com.guide.service.ImageService;

import java.util.List;

/**
 * Created by Max on 12.08.2016.
 */
@RestController
@RequestMapping("/images")
public class ImageRestController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<Image> getAllImages() {
        return imageService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Image getImageById(@PathVariable("id") Integer id) {
        return (Image) imageService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addNewImage(@RequestBody Image image) {
        imageService.create(image);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editImage(@PathVariable("id") Integer imageId, @RequestBody Image image) {
        image.setImageId(imageId);
        imageService.update(image);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteImage(@PathVariable("id") Integer imageId) {
        imageService.deleteById(imageId);
        return "redirect:";
    }
}
