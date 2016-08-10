package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.City;
import ua.com.guide.model.Place;
import ua.com.guide.service.PlaceService;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/places")
public class PlaceRestController {

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
    public String changePlace(@PathVariable("id") Integer id, @RequestBody Place place) {
        place.setPlaceId(id);
        placeService.update(place);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}/city", method = RequestMethod.GET)
    public City getCity(@PathVariable("id") Integer placeId){
        return placeService.getCity(placeId);
    }


}
