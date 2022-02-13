package com.example.vidT.controlls;

import com.example.vidT.models.User;
import com.example.vidT.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping
    public String userList(Model model){
        Iterable<User> users= userRepo.findAll();
        model.addAttribute("users", users);
        return "personalArea";
    }

    @GetMapping("{user}")
    public String userList(@PathVariable User user ,Model model){

        return "userEdit";
    }
}