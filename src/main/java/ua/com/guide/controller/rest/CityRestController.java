package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.City;
import ua.com.guide.model.Region;
import ua.com.guide.service.CityService;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/cities")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    private List<Region> getAllCities() {
        return cityService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private Region getCityById(@PathVariable("id") Integer id) {
        return (Region) cityService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    private String addCity(@RequestBody City city) {
        cityService.create(city);
        return "redirect:regions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    private String changeCity(@PathVariable("id") Integer id, @RequestBody City city) {
        city.setCityId(id);
        cityService.update(city);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private String deleteCity(@PathVariable("id") Integer id) {
        cityService.deleteById(id);
        return "redirect:";
    }
}