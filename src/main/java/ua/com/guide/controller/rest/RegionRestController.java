package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.City;
import ua.com.guide.model.Region;
import ua.com.guide.service.RegionService;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/api/regions")
public class RegionRestController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    private List<Region> getAllRegions() {
        return regionService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private Region getRegionById(@PathVariable("id") Integer id) {
        return (Region) regionService.getById(id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    private String addRegion(@RequestBody Region region) {
        regionService.create(region);
        return "redirect:regions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    private String editRegion(@PathVariable("id") Integer id, @RequestBody Region region) {
        region.setRegionId(id);
        regionService.update(region);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private String deleteRegion(@PathVariable("id") Integer id) {
        regionService.deleteById(id);
        return "redirect:";
    }


    @RequestMapping(value = "/{id}/cities", method = RequestMethod.GET)
    public List<City> getCities(@PathVariable("id") Integer id) {
        return regionService.getAllCityOfTheRegion(id);
    }

}
