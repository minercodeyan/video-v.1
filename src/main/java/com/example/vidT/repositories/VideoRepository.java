package com.example.vidT.repositories;
import com.example.vidT.models.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Video, Integer> {
    Video findById(long id);
    List<Video> findByName(String name);
}
