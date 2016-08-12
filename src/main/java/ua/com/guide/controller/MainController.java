package ua.com.guide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Max on 07.08.2016.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goIndex() {
        return "index";
    }

    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    public String goRegions(){
        return "regions";
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public String goCities(){
        return "cities";
    }
}
