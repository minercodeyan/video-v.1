package com.example.vidT.services.implementation;

import com.example.vidT.models.Video;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.Date;


@Service
public class TimerService {


    public long toftime(long d, long h, long m) {
        long allT;
        long milsInMin = 60000;
        long milsInHour = 3600000;
        long milsInDay = 86400000;
        allT = d * milsInDay + h * milsInHour + m * milsInMin;
        return allT;
    }


    public void timer(Video video, Model model) {
        long nowTime = new Date().getTime();
        long endTime = video.getTimer1();
        model.addAttribute("endTime", video.getTimer1());
        if (nowTime >= endTime) {
            model.addAttribute("allInf", video.getTextm());
        } else {
            model.addAttribute("allInf", "Время еще не пришло!");
        }
    }
}
