package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.Category;
import ua.com.guide.model.Post;
import ua.com.guide.service.CategoryService;

import java.util.List;

/**
 * Created by Max on 08.08.2016.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
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

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addNewCategory(@RequestBody Category category) {
        categoryService.create(category);
        return "redirect:category";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editCategory(@PathVariable("id") Integer categoryId, @RequestBody Category category) {
        category.setCategoryId(categoryId);
        categoryService.update(category);
        return "redirect:category";
    }

    // TODO: 11.08.2016 Test this method
    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public List<Post> getPostsByCategoryId(@PathVariable("id") Integer categoryId) {
        return categoryService.getPostsOfTheCategory(categoryId);
    }
}
