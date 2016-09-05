package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import ua.com.guide.model.City;
import ua.com.guide.model.Place;
import ua.com.guide.service.PlaceService;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/api/places")
public class PlaceRestController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<Place> getAllPlaces() {
        return placeService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Place getPlaceById(@PathVariable("id") Integer id) {
        return (Place) placeService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addPlace(@RequestBody Place place) {
        placeService.create(place);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editPlace(@PathVariable("id") Integer id, @RequestBody Place place) {
        place.setPlaceId(id);
        placeService.update(place);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePlace(@PathVariable("id") Integer id) {
        placeService.deleteById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}/city", method = RequestMethod.GET)
    public City getCity(@PathVariable("id") Integer placeId) {
        return placeService.getCity(placeId);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
