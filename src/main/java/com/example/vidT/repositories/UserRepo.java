package com.example.vidT.repositories;
import com.example.vidT.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

     User findByUsername(String username);
}
