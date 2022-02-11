package com.example.vidT.controlls;
import com.example.vidT.Service.EmailSenderService;
import com.example.vidT.Service.TimerService;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;



@Controller
public class VidController  {

    @Autowired
    private EmailSenderService service;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private TimerService timerService;

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Video> post= videoRepository.findAll();
        model.addAttribute("videos", post);
        return "all";
    }

    @GetMapping("/v/add")
    public String addv(Model model) {

        return "add";
    }

    @PostMapping("/v/add")
    public String addnew(@AuthenticationPrincipal User user, Video video,
                         Model model,
                         @RequestParam String filename ,
                         @RequestParam String textm,
                         @RequestParam long timer1 )
    throws IOException {
        Video post = new Video(filename,textm,user);
        post.setTimer1((long) new Date().getTime()+(timer1*60000));
        post.setAdminsend(false);
        videoRepository.save(post);
        return "redirect:/all";
    }

    @GetMapping("/all/{id}")
    public String details(@AuthenticationPrincipal User user, @PathVariable(value = "id") long id,Model model){
        Video post = videoRepository.findById(id);
       // ArrayList<Video> res1 = new ArrayList<>();
       // post.ifPresent(res1::add);
        model.addAttribute("post",post);
       // Video[] pe = res1.toArray(new Video[res1.size()]);
        timerService.timer(post,model);
        if(user!=null)
        if(user.getUsername().equals(post.getAuthorName()))
            model.addAttribute("b","1");

        return "/details";
    }

    @PostMapping("/all/{id}")
    public String delVid(@PathVariable(value = "id") long id,Model model){
        Video post = videoRepository.findById(id);
        videoRepository.delete(post);
        return "redirect:/all";
    }



}