package ua.com.guide.service;

import org.springframework.stereotype.Service;
import ua.com.guide.model.Category;

/**
 * Created by Max on 09.08.2016.
 */
@Service
public class CategoryService extends BasicService {

    public CategoryService() {
        super(Category.class);
    }
}
