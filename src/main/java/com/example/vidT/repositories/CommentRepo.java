package com.example.vidT.repositories;


import com.example.vidT.models.Comment;
import com.example.vidT.models.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Long> {
List<Comment> findAllByVideo(Video video);

}
