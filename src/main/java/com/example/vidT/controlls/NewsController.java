package com.example.vidT.controlls;

import com.example.vidT.models.News;
import com.example.vidT.models.User;
import com.example.vidT.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class NewsController {

    private final NewsRepo newsRepo;
    @Autowired
    public NewsController(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @GetMapping("/news/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adderNews(News news,Model model){
        model.addAttribute("newss",news);
        return "addNews";
    }
    @PostMapping("/news/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNews(@ModelAttribute("newss") @Valid News news,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "addNews";
        newsRepo.save(news);
        return "redirect:/user";
    }

    @GetMapping(value = "/profile/news")
    public String profileNews(@AuthenticationPrincipal User user, Model model) {
        Iterable<News> news =newsRepo.findAll();
        model.addAttribute("news",news)
                .addAttribute("user",user);
        return "profile/profileNews";
    }

}
