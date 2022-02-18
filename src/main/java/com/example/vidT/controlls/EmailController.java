package com.example.vidT.controlls;

import com.example.vidT.Service.EmailSenderService;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@Controller
public class EmailController {


    @Autowired
    private UserRepo userRepo;


    @PostMapping(value = "/activateUser")
    public String activateUser(@RequestParam String uni, @RequestParam String secret, @RequestParam Long userId, Model model) {
        if (uni.equals(secret)) {
            User user = userRepo.getById(userId);
            user.setActive(true);
            userRepo.save(user);
            return "redirect:/login";
        } else {
            model.addAttribute("massage", "код не верен");
            model.addAttribute("secret", secret);
            model.addAttribute("userId", userId);
            return "confirmemail";
        }
    }


}
