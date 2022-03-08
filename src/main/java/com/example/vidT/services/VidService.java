package com.example.vidT.services;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service

public class VidService {

    private UserRepo userRepo;
    private VideoRepository videoRepository;

    @Autowired
    public VidService(UserRepo userRepo, VideoRepository videoRepository) {
        this.userRepo = userRepo;
        this.videoRepository = videoRepository;
    }

    @Transactional
    public Set<Video> addSet(User user) {
        Set<Video> videoSet = user.getVideos();
        return videoSet;
    }


}
