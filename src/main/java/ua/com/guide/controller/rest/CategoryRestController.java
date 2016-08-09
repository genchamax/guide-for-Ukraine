package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.guide.dao.AbstractDao;
import ua.com.guide.model.Category;

import java.util.List;

/**
 * Created by Max on 08.08.2016.
 */
@Controller
@RequestMapping("/category")
public class CategoryRestController {
    @Autowired
    @Qualifier("categoryDao")
    private AbstractDao categoryDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return (Category) categoryDao.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable Integer id) {
        categoryDao.delete(categoryDao.getById(id));
        return "redirect:category";
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String addNewCategory(@ModelAttribute("category") Category category ) {
//        categoryDao.update(category);
//        return "redirect:category";
//    }

}
