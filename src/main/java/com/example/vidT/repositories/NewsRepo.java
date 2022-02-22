package com.example.vidT.repositories;
import com.example.vidT.models.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepo extends CrudRepository<News,Long> {
    News findById(long id);
}
