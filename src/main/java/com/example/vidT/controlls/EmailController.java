package com.example.vidT.controlls;


import com.example.vidT.Service.EmailSenderService;
import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Set;


@Controller
public class EmailController {
    @Autowired
    private EmailSenderService service;
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/mail/{user}")
    public String sendmail(@PathVariable User user,
                           Model model) {
        Set<Video> vid = user.getVideos();
        for ( Video v: vid) {
           if(v.isAdminsend()==false)
               if(v.getTimer1()<new Date().getTime()){
                  // service.sendSimpleEmail(user.getEmail(),v.getFilename()+"пришло!","");
                   System.out.println(user.getEmail());
            v.setAdminsend(true);

            videoRepository.save(v);
        }}
        return "redirect:/user";
    }
}
