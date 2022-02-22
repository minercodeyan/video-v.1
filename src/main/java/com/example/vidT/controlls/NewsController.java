package com.example.vidT.controlls;

import com.example.vidT.models.News;
import com.example.vidT.models.User;
import com.example.vidT.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

    private NewsRepo newsRepo;
    @Autowired
    public NewsController(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @GetMapping("/news/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adderNews(Model model){

        return "addNews";
    }
    @PostMapping("/news/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNews(@RequestParam String name,@RequestParam String content,Model model){
        News news = new News(name,content);
        newsRepo.save(news);
        return "redirect:/user";
    }

    @GetMapping(value = "/profile/news")
    public String profileNews(@AuthenticationPrincipal User user, Model model) {
        Iterable<News> news =newsRepo.findAll();
        model.addAttribute("news",news);
        model.addAttribute("user",user);
        return "profile/profileNews";
    }

}
