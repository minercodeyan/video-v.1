package com.example.vidT.controlls;

import com.example.vidT.Service.VidService;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class ProfileController {


    private final VideoRepository videoRepository;
    private final UserRepo userRepo;
    private final VidService vidService;


    @Autowired
    public ProfileController(VideoRepository videoRepository, UserRepo userRepo, VidService vidService) {
        this.videoRepository = videoRepository;
        this.userRepo = userRepo;
        this.vidService = vidService;
    }


    @GetMapping("/profiles/{username}")
    public String profileMain(@AuthenticationPrincipal User curruser,
                              @PathVariable String username,
                              Model model) {
        User user = userRepo.findByUsername(username);
       Set<Video> videos = vidService.addSet(user);
       model.addAttribute("videos",videos);
       model.addAttribute("currpage",1);
       if(curruser!=null)
       if(username.equals(curruser.getUsername()))
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



}
