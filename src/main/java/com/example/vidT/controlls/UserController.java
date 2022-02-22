package com.example.vidT.controlls;

import com.example.vidT.Service.EmailSenderService;
import com.example.vidT.Service.UserService;
import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private UserRepo userRepo;
    private EmailSenderService service;
    private UserService userService;
    private VideoRepository videoRepository;

    @Autowired
    public UserController(UserRepo userRepo, EmailSenderService service, UserService userService, VideoRepository videoRepository) {
        this.userRepo = userRepo;
        this.service = service;
        this.userService = userService;
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public String userList(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "personalArea";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userEdit(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user, Model model) {

        System.out.println(user);
        User userfromDb = userRepo.findByUsername(username);

        if (userfromDb != null&&username.equals(user.getUsername())==false) {
            model.addAttribute("err", "такое имя уже есть");
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
            return "userEdit";
        }
        userService.editUser(username,user,form);
        return "redirect:/user";
    }

    @PostMapping("/block")
    public String userBlock(@RequestParam("userId") User user){
        user.setActive(false);
        userRepo.save(user);
        return "redirect:/user/"+user.getId();
    }


    @PostMapping("/mail/{userid}")
    public String sendmail(@PathVariable(value = "userid") User user,
                           Model model) {
        Set<Video> vid = videoRepository.findByAuthor(user);
        if(!vid.isEmpty())
        for (Video v : vid) {
                if (v.getTimer1() < new Date().getTime()) {
                    // service.sendSimpleEmail(user.getEmail(),v.getFilename()+"пришло!","");
                    System.out.println(user.getEmail());
                    v.setAdminsend(true);
                    videoRepository.save(v);
                }
        }
        return "redirect:/user";
    }
}