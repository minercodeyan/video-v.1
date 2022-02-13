package com.example.vidT.Service;

import com.example.vidT.models.Video;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class TimerService {

    public void timer(Video video,Model model) {
        long nowtime = (long) new Date().getTime();
        long endtime = video.getTimer1() ;
        model.addAttribute("endtime", video.getTimer1());
        if (nowtime >= endtime) {
            model.addAttribute("a", "1");
            model.addAttribute("allinf", video.getTextm());
        } else {
            model.addAttribute("a", "0");
            model.addAttribute("allinf", "время еще не пришло!");
        }

    }
}
