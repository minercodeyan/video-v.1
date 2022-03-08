package com.example.vidT.controlls;

import com.example.vidT.services.FileService;
import com.example.vidT.services.TimerService;
import com.example.vidT.services.UserService;
import com.example.vidT.exceptions.NotFoundException;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.VideoRepository;
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
import java.util.*;

@Controller
public class VidController {


    private VideoRepository videoRepository;
    private TimerService timerService;
    private UserService userService;
    private FileService fileService;


    @Autowired
    public VidController(VideoRepository videoRepository, TimerService timerService, UserService userService, FileService fileService) {
        this.videoRepository = videoRepository;
        this.timerService = timerService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public String all(@PageableDefault(sort={ "id"},
            direction = Sort.Direction.ASC) Pageable pageable,
                      Model model) {
        Page<Video> page = videoRepository.findAll(pageable);
        model.addAttribute("videos", page);
        return "all";
    }

    @GetMapping("/v/add")
    public String addv(Model model) {

        return "add";
    }

    @PostMapping("/v/add")
    public String addnew(@AuthenticationPrincipal User user,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "textm") String content,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam long timerDay,
                         @RequestParam long timerHour,
                         @RequestParam long timerMin, Model model)
            throws IOException {
        if (userService.maxCount(user) == true) {
            Video post = new Video(name.trim(), content.trim(), user);
            post.setTimer1((long) new Date().getTime() +
                    (timerService.toftime(timerDay, timerHour, timerMin)));
            post.setAdminsend(false);
            fileService.fileLoader(file,post);
            videoRepository.save(post);
            return "redirect:/all";
        } else
            return "add";
    }


    @GetMapping("/all/{id}")
    public String details(@AuthenticationPrincipal User user, @PathVariable(value = "id") long id, Model model) {
        Video post = Optional.ofNullable(videoRepository.findById(id)).orElseThrow(NotFoundException::new);
        model.addAttribute("post", post);
        timerService.timer(post, model);
        if (user != null)
            if (user.getUsername().equals(post.getAuthorName()))
                model.addAttribute("b", "1");
        return "details";
    }

    @PostMapping("/all/{id}/del")
    public String delPost(@PathVariable(value = "id")long id, @RequestParam int currpage, Model model) {
        Video post = videoRepository.findById(id);
        videoRepository.delete(post);
        if(currpage==1)
        return "redirect:/profile/"+currpage;
        if(currpage==2)
            return "redirect:/profile/"+currpage;
        return "redirect:/all";
    }
}