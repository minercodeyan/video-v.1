package com.example.vidT.repositories;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;


@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
   Page<Video> findAll(Pageable pageable);

    Video findById(long id);
    List<Video> findByName(String name);
    List<Video> findAllByAuthor(User user);

    @Query("from Video as v where v.author = :author and v.isAdminsend=false")
    Set<Video> findByAuthor(@Param("author") User user);

}
