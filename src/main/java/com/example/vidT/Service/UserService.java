package com.example.vidT.Service;

import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @PostMapping("/reg")
    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null || !user.getPassword().equals(user.getPassword2())) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));//set с 1 значением
        userRepo.save(user);
        return true;
    }

    }


