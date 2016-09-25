package ua.com.guide.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.com.guide.model.Comment;

/**
 * Created by Max on 10.08.2016.
 */
public class CommentService extends BasicService {
    public CommentService() {
        super(Comment.class);
    }

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
    @Override
    public void delete(Object entity) {
        super.delete(entity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }
}
