package ua.com.guide.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;
import ua.com.guide.model.Comment;
import ua.com.guide.service.CommentService;

/**
 * Created by Max on 10.08.2016.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String addComment(@RequestBody Comment comment) {
        commentService.create(comment);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editComment(@PathVariable("id") Integer commentId, @RequestBody Comment comment) {
        comment.setCommentId(commentId);
        commentService.update(comment);
        return "redirect:";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteComment(@PathVariable("id") Integer id) {
        commentService.deleteById(id);
        return "redirect:";
    }

}
