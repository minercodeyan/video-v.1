package com.example.vidT.services;

import com.example.vidT.models.Comment;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;


import java.util.List;


public interface CommentService {
    List<Comment> allComments(Video post);
    void addComment(User user, String content, Video video);
}
