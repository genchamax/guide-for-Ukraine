package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.guide.model.Post;
import ua.com.guide.model.enums.ImagePathLevel;

/**
 * Created by Max on 10.08.2016.
 */
public class PostService extends BasicService {

    public PostService() {
        super(Post.class);
    }

    @Autowired
    private ImageService imageService;

    public void deleteById(String root, Integer id) {
        imageService.deleteImagesInPath(root, ImagePathLevel.POST, id);
        deleteById(id);
    }
}
