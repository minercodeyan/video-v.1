package com.example.vidT.services;

import com.example.vidT.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
import java.util.Optional;

public interface UserService extends UserDetailsService{
    int addUser(User user);
    Optional<User> getUserByUserName(String username);
    void editUser(String username, User user, Map<String, String> form);
    void sendMailsForUser(User user);
    void blockUser(User user);
    void changePass(User user,String newPass);

    void makeUserActive(Long userId);
}
