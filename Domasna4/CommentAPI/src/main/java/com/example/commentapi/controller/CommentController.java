package com.example.commentapi.controller;

import com.example.commentapi.model.Comment;
import org.springframework.web.bind.annotation.*;
import com.example.commentapi.service.CommentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public void saveComment(@RequestParam Long wineryId, @RequestParam Long userID, @RequestParam String userName, @RequestParam String comment, HttpServletResponse response) throws IOException {
        commentService.save(comment, userID, userName, wineryId);
         String url = "redirect:/wines/" + wineryId.toString();
         response.sendRedirect(url);
    }
    @GetMapping("/{id}")
    public List<Comment> getComments(@PathVariable Long id){
        return commentService.findAllByWineryId(id);
    }
}
