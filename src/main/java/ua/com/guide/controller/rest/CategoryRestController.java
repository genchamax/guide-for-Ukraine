package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

    // TODO: 16.08.2016 Fix all APIs
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public @ResponseBody List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Category getCategoryById(@PathVariable("id") Integer id) {
        return (Category) categoryService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addNewCategory(@RequestBody Category category) {
        categoryService.create(category);
        return "/admin/categories";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editCategory(@PathVariable("id") Integer categoryId, @RequestBody Category category) {
        category.setCategoryId(categoryId);
        categoryService.update(category);
        return "redirect:/admin/categories";
    }

    // TODO: 11.08.2016 Test this method
    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public @ResponseBody List<Post> getPostsByCategoryId(@PathVariable("id") Integer categoryId) {
        return categoryService.getPostsOfTheCategory(categoryId);
    }
}
