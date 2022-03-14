package com.example.vidT.controlls;

import com.example.vidT.services.PostService;
import com.example.vidT.services.UserService;
import com.example.vidT.services.implementation.UserServiceImpl;
import com.example.vidT.services.implementation.VidServiceImpl;
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
import java.util.Set;

@Controller
public class ProfilesController {

    private final UserService userService;
    private final PostService postService;


    @Autowired
    public ProfilesController(UserServiceImpl userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @GetMapping("/profiles/{username}")
    public String profileMain(@AuthenticationPrincipal User curUser,
                              @PathVariable String username,
                              Model model) {
        User user = userService.getUserByUserName(username).orElseThrow();
        Set<Video> videos = postService.addSet(user);
        model.addAttribute("videos", videos)
                .addAttribute("userName", user.getUsername())
                .addAttribute("currpage", 1);
        if (curUser != null)
            if (username.equals(curUser.getUsername()))
                model.addAttribute("isCurrent", true);
        return "profile/profilePart1";
    }


    @GetMapping("/profile/options")
    public String profileJustiffy(@AuthenticationPrincipal User user, Model model) {
        Iterable<Video> videos = postService.getUserPosts(user);
        model.addAttribute("videos", videos)
                .addAttribute("currPage", 2)
                .addAttribute("user", user);
        return "profile/profilePart2";
    }

    @PostMapping("/profile/options")
    public String profileDark(@RequestParam Map<String, String> form,
                              Model model, HttpSession session) {

        if (form.get("form") != null) {
            session.setAttribute("color", "darkTheme.css");
            session.setAttribute("checked", true);
        } else {
            session.setAttribute("color", null);
            session.setAttribute("checked", false);
        }

        return "redirect:/all";
    }

    @PostMapping("/profile/changePass")
    public String profileDark(@AuthenticationPrincipal User currUser,
                              @RequestParam String oldPass,
                              @RequestParam String newPass,
                              @RequestParam String confPass,
                              Model model) {
        if (!oldPass.equals(currUser.getPassword())) {
            model.addAttribute("err1", "");
            return "profile/profilePart2";
        }
        if (!newPass.equals(confPass)) {
            model.addAttribute("err2", "");
            return "profile/profilePart2";
        }
        userService.changePass(currUser, newPass);
        return "redirect:/all";
    }

}
