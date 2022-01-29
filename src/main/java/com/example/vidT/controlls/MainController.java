package com.example.vidT.controlls;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MainController {
    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/")
    public String home1(Model model) {
        model.addAttribute("title", "Главная");
        return "home1";
    }
    @GetMapping("/faq")
    public String Faq(Model model) {
        model.addAttribute("title", "Faq");
        return "faq";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter , Model model){
        Iterable<Video> vid;
        if(filter!=null && !filter.isEmpty())
            vid = videoRepository.findByFilename(filter);
        else
            vid=videoRepository.findAll();
        model.addAttribute("videos",vid);
        return "/all";
    }
}