package ua.com.guide.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String goRegions() {
        return "regions";
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public String goCities() {
        return "cities";
    }

    @RequestMapping(value = "/places", method = RequestMethod.GET)
    public String goPlaces() {
        return "places";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String goPosts() {
        return "posts";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String goAdmin() {
        return "admin/index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String goTest() {
        return "/test/sidenav";
    }

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String goAdminCategories() {
        return "/admin/categories";
    }

    @RequestMapping(value = "test/post/new", method = RequestMethod.GET)
    public String goNewPost() {
        return "test/newPost";
    }

    @RequestMapping(value = "test/upload")
    public String goUpload() {
        return "test/FIleUpload";
    }

    @RequestMapping(value = "/admin/index")
    public String goToAdminIndex() {
        return "admin/index";
    }

    @RequestMapping(value = "/login")
    public String goLogin() {
        return "login/login";
    }

    @RequestMapping(value = "/403")
    public String go403() {
        return "login/403";
    }
}
