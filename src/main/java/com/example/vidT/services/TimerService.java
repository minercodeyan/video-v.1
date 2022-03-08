package com.example.vidT.services;

import com.example.vidT.models.Video;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.Date;


@Service
public class TimerService {
    private static long milsinmin = 60000;
    private static long milsinhour = 3600000;
    private static long milsinday = 86400000;


    public long toftime(long d, long h, long m) {
        long allt;
        allt = d * milsinday + h * milsinhour + m * milsinmin;
        return allt;
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
