package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import ua.com.guide.model.Post;
import ua.com.guide.service.PostService;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/api/posts")
public class PostRestController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getPostById(@PathVariable("id") Integer postId) {
        return (Post) postService.getById(postId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePostById(@PathVariable("id") Integer id) {
        postService.deleteById(servletContext.getRealPath("/"), id);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public void addPost(@RequestBody Post post) {
        postService.create(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editPost(@PathVariable("id") Integer id, @RequestBody Post post) {
        post.setPostId(id);
        postService.update(post);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

