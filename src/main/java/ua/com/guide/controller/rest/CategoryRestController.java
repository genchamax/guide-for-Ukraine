package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.guide.model.Category;
import ua.com.guide.service.CategoryService;

import java.util.List;

/**
 * Created by Max on 08.08.2016.
 */
@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        System.out.println(categoryService.getAll());
        return categoryService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return (Category) categoryService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:category";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addNewCategory(Category category) {
        categoryService.update(category);
        return "redirect:category";
    }


}
