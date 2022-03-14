package com.example.vidT.services.implementation;


import com.example.vidT.models.Comment;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.CommentRepo;
import com.example.vidT.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentsServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentsServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public List<Comment> allComments(Video post){
        return commentRepo.findAllByVideo(post);
    }

    @Override
    public void addComment(User user,String content,Video video){
        Comment comment=new Comment(user,content,video);
        commentRepo.save(comment);}
}
