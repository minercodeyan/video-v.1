package com.example.vidT.repositories;
import com.example.vidT.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

     Optional<User> findByUsername(String username);
     User findByEmail(String email);
}
