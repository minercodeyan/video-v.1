package com.example.vidT.Service;

import com.example.vidT.models.Video;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;


@Service
public class TimerService {
    private static long milsinmin = 60000;
    private static long milsinhour = 3600000;
    private static long milsinday = 86400000;
    private boolean timerIsEnd = false;

    public long toftime(long d, long h, long m) {
        long allt;
        allt = d * milsinday + h * milsinhour + m * milsinmin;
        return allt;
    }

    public void timer(Video video, Model model) {
        long nowtime = (long) new Date().getTime();
        long endtime = video.getTimer1();
        model.addAttribute("endtime", video.getTimer1());
        if (nowtime >= endtime) {
            model.addAttribute("allinf", video.getTextm());
        } else {
            model.addAttribute("allinf", "Время еще не пришло!");
        }
    }
}
