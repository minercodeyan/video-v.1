package com.example.vidT.controlls;

import com.example.vidT.services.UserService;
import com.example.vidT.services.implementation.UserServiceImpl;
import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import com.example.vidT.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersController {

    private final UserRepo userRepo;

    private final UserService userService;

    @Autowired
    public UsersController(UserRepo userRepo, UserServiceImpl userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "personalArea";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user)
                .addAttribute("roles", Role.values());
        return "userEdit";
    }

  @PostMapping
    public String userEdit(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user, Model model) {
        User userFromDb = userRepo.findByUsername(username).orElse(null);
        if (userFromDb != null && !username.equals(user.getUsername())) {
            model.addAttribute("err", "такое имя уже есть")
                    .addAttribute("user", user)
                    .addAttribute("roles", Role.values());
            return "userEdit";
        }
        else{
        userService.editUser(username,user,form);
        return "redirect:/user";
        }
    }



    @PostMapping("/block")
    public String userBlock(@RequestParam("userId") User user){
        userService.blockUser(user);
        return "redirect:/user/"+user.getId();
    }


    @PostMapping("/mail/{userid}")
    public String sendmail(@PathVariable(value = "userid") User user) {
        userService.sendMailsForUser(user);
        return "redirect:/user";
    }
}