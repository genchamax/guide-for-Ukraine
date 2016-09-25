package ua.com.guide.service;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public Object create(Object entity) {
        return super.create(entity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public Object update(Object entity) {
        return super.update(entity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public void delete(Object entity) {
        super.delete(entity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    public List<Post> getPostsOfTheCategory(Integer categoryId) {
        Category category = (Category) getById(categoryId);
        return category.getPosts();
    }
}
