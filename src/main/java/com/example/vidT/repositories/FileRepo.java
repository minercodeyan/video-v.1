package com.example.vidT.repositories;

import com.example.vidT.models.FileMy;
import org.springframework.data.repository.CrudRepository;

public interface FileRepo extends CrudRepository<FileMy,Long> {
}
