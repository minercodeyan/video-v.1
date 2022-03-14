package com.example.vidT.controlls;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping(value = "/add")
    public String addComment(@AuthenticationPrincipal User user,
                             @RequestParam String content,
                             @RequestParam("post") Video video) {
        commentService.addComment(user, content, video);
        return "redirect:/all/" + video.getId();
    }

    @PostMapping(value = "/del")
    public String delComment(@RequestParam(name = "post") Long id) {


        return "redirect:/all/" + id;
    }
}
