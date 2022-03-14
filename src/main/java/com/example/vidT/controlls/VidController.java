package com.example.vidT.controlls;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.services.CommentService;
import com.example.vidT.services.PostService;
import com.example.vidT.services.implementation.CommentsServiceImpl;
import com.example.vidT.services.implementation.TimerService;
import com.example.vidT.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class VidController {

    private final TimerService timerService;
    private final UserServiceImpl userService;
    private final PostService postService;
    private final CommentService commentsService;

    @Autowired
    public VidController(TimerService timerService,
                         UserServiceImpl userService,
                         CommentsServiceImpl commentsService,
                         PostService postService) {
        this.timerService = timerService;
        this.userService = userService;
        this.commentsService = commentsService;
        this.postService = postService;
    }

    @GetMapping("/all")
    public String all(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable,
                      Model model) {
        Page<Video> page = postService.getPostPage(pageable);
        model.addAttribute("videos", page);
        return "all";
    }

    @GetMapping("/v/add")
    public String addV() {

        return "add";
    }

    @PostMapping("/v/add")
    public String addnew(@AuthenticationPrincipal User user,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "textm") String content,
                         @RequestParam("file1") MultipartFile file1,
                         @RequestParam("file2") MultipartFile file2,
                         @RequestParam("file3") MultipartFile file3,
                         @RequestParam long timerDay,
                         @RequestParam long timerHour,
                         @RequestParam long timerMin, Model model)
            throws IOException{
        if (userService.maxCount(user)) {
            postService.addPost(name.trim(), content.trim(), user, timerDay,
                    timerHour, timerMin, file1, file2, file3);
            return "redirect:/all";
        } else
            model.addAttribute("error","error");
            return "add";
    }


    @GetMapping("/all/{id}")
    public String details(@AuthenticationPrincipal User user,
                          @PathVariable(value = "id") long id,
                          Model model) {
        Video post = postService.getPostById(id);
        model.addAttribute("post", post);
        timerService.timer(post, model);
        if (user != null)
            if (user.getUsername().equals(post.getAuthorName()))
                model.addAttribute("b", "1");
        model.addAttribute("comments", commentsService.allComments(post));
        return "details";
    }

    @PostMapping("/all/{id}/del")
    public String delPost(@PathVariable(value = "id") long id,
                          @RequestParam int currPage) {
        postService.deletePost(id);
        if (currPage == 2)
            return "redirect:/profile/option";
        return "redirect:/all";
    }
}