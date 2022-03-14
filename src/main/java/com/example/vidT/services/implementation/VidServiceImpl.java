package com.example.vidT.services.implementation;

import com.example.vidT.exceptions.NotFoundException;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import com.example.vidT.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service

public class VidServiceImpl implements PostService {


    private final VideoRepository videoRepository;
    private final FileService fileService;
    private final TimerService timerService;

    @Autowired
    public VidServiceImpl( VideoRepository videoRepository,
                          FileService fileService, TimerService timerService) {

        this.videoRepository = videoRepository;
        this.fileService = fileService;
        this.timerService = timerService;
    }

    @Override
    public void addPost(String name, String content,
                        User user,
                        long timerDay,
                        long timerHour,
                        long timerMin,
                        MultipartFile file1,
                        MultipartFile file2,
                        MultipartFile file3) throws IOException {
        Video post = new Video(name.trim(), content.trim(), user);
        post.setTimer1((long) new Date().getTime() + (timerService.toftime(timerDay, timerHour, timerMin)));
        post.setAdminsend(false);
        videoRepository.save(post);
        fileService.fileLoader(file1,post);
        fileService.fileLoader(file2,post);
        fileService.fileLoader(file3,post);
    }

    public Video getPostById(long id){
        return Optional.ofNullable(videoRepository.findById(id)).orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<Video> getPostPage(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    public void deletePost(long id){
        Video post = videoRepository.findById(id);
        videoRepository.delete(post);
    }

    @Transactional
    public Set<Video> addSet(User user) {
        return user.getVideos();
    }

    @Override
    public Iterable<Video> getUserPosts(User user) {
        return videoRepository.findAllByAuthor(user);
    }


}
