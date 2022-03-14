package com.example.vidT.services;

import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface PostService {
    void deletePost(long id) ;

    void addPost(String trim, String trim1, User user,
                 long timerDay, long timerHour, long timerMin,
                 MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    Video getPostById(long id) ;

    Page<Video> getPostPage(Pageable pageable);

    Set<Video> addSet(User user);

    Iterable<Video> getUserPosts(User user);
}
