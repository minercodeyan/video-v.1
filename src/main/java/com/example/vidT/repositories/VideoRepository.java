package com.example.vidT.repositories;
import com.example.vidT.models.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends CrudRepository<Video, Integer> {
    Optional<Video> findById(long id);
    List<Video> findByFilename(String filename);
}
