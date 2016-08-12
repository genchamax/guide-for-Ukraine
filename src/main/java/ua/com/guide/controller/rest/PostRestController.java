package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.Post;
import ua.com.guide.service.PostService;

import java.util.List;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/posts")
public class PostRestController {

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
    public String deletePostById(@PathVariable("id") Integer id) {
        postService.deleteById(id);
        return "redirect:";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addPost(@RequestBody Post post) {
        postService.create(post);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editPost(@PathVariable("id") Integer id, @RequestBody Post post) {
        post.setPostId(id);
        postService.update(post);
        return "redirect:";
    }



}

