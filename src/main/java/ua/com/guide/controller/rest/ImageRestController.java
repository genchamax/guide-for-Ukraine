package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.guide.service.ImageService;

/**
 * Created by Max on 12.08.2016.
 */
@RestController
@RequestMapping("/api/images")
public class ImageRestController {

    @Autowired
    private ImageService imageService;


}
