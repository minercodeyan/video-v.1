package com.example.vidT.repositories;
import com.example.vidT.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

     User findByUsername(String username);
     User findByEmail(String email);
}
