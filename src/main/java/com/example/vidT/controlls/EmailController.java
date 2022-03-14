package com.example.vidT.controlls;

import com.example.vidT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmailController {


    private final UserService userService;

    @Autowired
    public EmailController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/activateUser")
    public String activateUser(@RequestParam String uni,
                               @RequestParam String secret,
                               @RequestParam Long userId, Model model) {
        if (uni.equals(secret)) {
            userService.makeUserActive(userId);
            return "redirect:/login";
        } else {
            model.addAttribute("massage", "код не верен")
                    .addAttribute("secret", secret)
                    .addAttribute("userId", userId);
            return "confirmemail";
        }
    }


}
