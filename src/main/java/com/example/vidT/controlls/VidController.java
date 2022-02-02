package com.example.vidT.controlls;
import com.example.vidT.Service.EmailSenderService;
import com.example.vidT.Service.UserService;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;

import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class VidController  {

    @Autowired
    private EmailSenderService service;
    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Video> post= videoRepository.findAll();
        model.addAttribute("videos", post);
        return "all";
    }

    @GetMapping("/v/add")
    public String addv(@AuthenticationPrincipal User user,Model model) {

        return "add";
    }

    @PostMapping("/v/add")
    public String addnew(@AuthenticationPrincipal User user, @RequestParam String filename ,
                          @RequestParam String media_file_id,
                         @RequestParam int timer , Model model){
        Video post = new Video(filename,media_file_id,timer,user);
        post.setTimer1((long) new Date().getTime());
        videoRepository.save(post);
        return "redirect:/all";
    }

    @GetMapping("/all/{id}")
    public String details(@AuthenticationPrincipal User user, @PathVariable(value = "id") long id,Model model){
        Optional<Video> post = videoRepository.findById(id);
        ArrayList<Video> res1 = new ArrayList<>();
        post.ifPresent(res1::add);
        model.addAttribute("post",res1);
      Video[] pe = res1.toArray(new Video[res1.size()]);
        model.addAttribute("timeall",pe[0].getTimer());
        model.addAttribute("starttime",pe[0].getTimer1());
        long nowtime = (long) new Date().getTime();
        long endtime =pe[0].getTimer1()+pe[0].getTimer()*60000;
        if(user!=null)
        if(user.getUsername().equals(pe[0].getAuthorName()))
            model.addAttribute("b","1");
        if(nowtime>=endtime){
            model.addAttribute("a","1");
            model.addAttribute("allinf",pe[0].getMedia_file_id());}
        else{
            model.addAttribute("a","0");
            model.addAttribute("allinf","время еще не пришло!");}
        return "/details";
    }

    @PostMapping("/all/{id}")
    public String delVid(@PathVariable(value = "id") long id,Model model){
        Video post = videoRepository.findById(id).orElseThrow();
        videoRepository.delete(post);
        return "redirect:/all";
    }

}