package ua.com.guide.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.guide.model.Category;
import ua.com.guide.model.Post;

import java.util.List;

/**
 * Created by Max on 09.08.2016.
 */
@Service
public class CategoryService extends BasicService {

    public CategoryService() {
        super(Category.class);
    }

    public List<Post> getPostsOfTheCategory(Integer categoryId) {
        Category category = (Category) getById(categoryId);
        return category.getPosts();
    }
}
