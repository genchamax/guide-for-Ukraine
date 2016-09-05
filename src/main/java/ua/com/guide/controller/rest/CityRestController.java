package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import ua.com.guide.model.City;
import ua.com.guide.model.Place;
import ua.com.guide.model.Region;
import ua.com.guide.service.CityService;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/api/cities")
public class CityRestController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private CityService cityService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    private List<City> getAllCities() {
        return cityService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private City getCityById(@PathVariable("id") Integer id) {
        return (City) cityService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    private String addCity(@RequestBody City city, @RequestParam(value = "regionId", required = false) Integer regionId) {
        cityService.create(city);
        return "redirect:regions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    private String editCity(@PathVariable("id") Integer id, @RequestBody City city) {
        city.setCityId(id);
        cityService.update(city);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private String deleteCity(@PathVariable("id") Integer id) {
        cityService.deleteById(servletContext.getRealPath("/"), id);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}/region", method = RequestMethod.GET)
    public Region getRegion(@PathVariable("id") Integer id) {
        return cityService.getRegion(id);
    }

    // TODO: 11.08.2016 Test this method
    @RequestMapping(value = "/{id}/places", method = RequestMethod.GET)
    public List<Place> getPlaces(@PathVariable("id") Integer cityId) {
        return cityService.getAllPlacesOfTheCity(cityId);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
