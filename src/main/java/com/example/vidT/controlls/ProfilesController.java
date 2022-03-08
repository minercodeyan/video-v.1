package com.example.vidT.controlls;

import com.example.vidT.services.VidService;
import com.example.vidT.exceptions.NotFoundException;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
public class ProfilesController {


    private final VideoRepository videoRepository;
    private final UserRepo userRepo;
    private final VidService vidService;


    @Autowired
    public ProfilesController(VideoRepository videoRepository, UserRepo userRepo, VidService vidService) {
        this.videoRepository = videoRepository;
        this.userRepo = userRepo;
        this.vidService = vidService;
    }


    @GetMapping("/profiles/{username}")
    public String profileMain(@AuthenticationPrincipal User curUser,
                              @PathVariable String username,
                              Model model) {
        User user = Optional.ofNullable(userRepo.findByUsername(username)).
                orElseThrow(NotFoundException::new);
       Set<Video> videos = vidService.addSet(user);
       model.addAttribute("videos",videos);
       model.addAttribute("userName",user.getUsername());
       model.addAttribute("currpage",1);
       if(curUser!=null)
       if(username.equals(curUser.getUsername()))
           model.addAttribute("isCurrent",true);
       return "profile/profilePart1";
    }




    @GetMapping("/profile/options")
    public String profileJusify(@AuthenticationPrincipal User user, Model model) {
        Iterable<Video> videos = videoRepository.findAllByAuthor(user);
        model.addAttribute("videos",videos);
        model.addAttribute("currpage",2);
        model.addAttribute("user",user);
        return "profile/profilePart2";
    }

    @PostMapping("/profile/options")
    public String profileDark(@RequestParam Map<String, String> form,
                              Model model, HttpSession session) {

        if (form.get("form") != null) {
        session.setAttribute("color","darkTheme.css");
        session.setAttribute("checked",true);}
        else {
            session.setAttribute("color",null);
            session.setAttribute("checked",false);}

        return "redirect:/all";
    }

    @PostMapping("/profile/changePass")
    public String profileDark(@AuthenticationPrincipal User user,
                              @RequestParam String oldPass,
                              @RequestParam String newPass,
                              @RequestParam String confPass,
                              Model model) {
        if(oldPass.equals(user.getPassword())==false||newPass.equals(confPass)==false)
            return "profile/profilePart2";
        user.setPassword(newPass);
        userRepo.save(user);
        return "redirect:/all";
    }

}
