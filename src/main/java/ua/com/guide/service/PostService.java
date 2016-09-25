package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import ua.com.guide.model.Post;
import ua.com.guide.util.enums.ImagePathLevel;

/**
 * Created by Max on 10.08.2016.
 */
public class PostService extends BasicService {

    public PostService() {
        super(Post.class);
    }

    @Autowired
    private ImageService imageService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public Object create(Object entity) {
        return super.create(entity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public Object update(Object entity) {
        return super.update(entity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void deleteById(String root, Integer id) {
        imageService.deleteImagesInPath(root, ImagePathLevel.POST, id);
        deleteById(id);
    }
}
