package com.example.vidT.services.implementation;

import com.example.vidT.exceptions.NotFoundException;
import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements com.example.vidT.services.UserService {


    private final UserRepo userRepo;

    private final VideoRepository videoRepository;

    private final EmailSenderService emailSenderService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, VideoRepository videoRepository, EmailSenderService emailSenderService) {
        this.userRepo = userRepo;
        this.videoRepository = videoRepository;
        this.emailSenderService = emailSenderService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("lol"));
    }

    @Override
    public int addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername()).orElse(null);
        assert false;
        if (userFromDb != null) {
            return 1;
        }
        User userCheckEmail = userRepo.findByEmail(user.getEmail());
        if (userCheckEmail!=null) {
            return 2;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));//set с 1 значением
        userRepo.save(user);
        return 0;
    }


    public boolean maxCount(User currentUser) {
        List<Video> s = videoRepository.findAllByAuthor(currentUser);
        Role[] arr = currentUser.getRoles().toArray(new Role[currentUser.getRoles().size()]);
        return s.size() < arr[currentUser.getRoles().size() - 1].getI();
    }

   public void editUser(String username, User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.setUsername(username);
        userRepo.save(user);
    }


    public void sendMailsForUser(User user) {
        Set<Video> vid = videoRepository.findByAuthor(user);
        if (!vid.isEmpty())
            for (Video v : vid) {
                if (v.getTimer1() < new Date().getTime()) {
                    emailSenderService.sendSimpleEmail(user.getEmail(), v.getFilename() + "пришло!", "");
                    System.out.println(user.getEmail());
                    v.setAdminsend(true);
                    videoRepository.save(v);
                }
            }
    }

    @Override
    public void blockUser(User user) {
        user.setActive(false);
        userRepo.save(user);
    }

    @Override
    public void changePass(User user,String newPass) {
        user.setPassword(newPass);
        userRepo.save(user);
    }

    @Override
    public void makeUserActive(Long userId) {
        User user = userRepo.getById(userId);
        user.setActive(true);
        userRepo.save(user);
    }


    public Optional<User> getUserByUserName(String username) {
       return Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(NotFoundException::new);
    }
}


