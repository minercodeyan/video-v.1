package com.example.vidT.Service;

import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import com.example.vidT.models.Video;
import com.example.vidT.repositories.UserRepo;
import com.example.vidT.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Collections;
import java.util.List;



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


    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null || !user.getPassword().equals(user.getPassword2())) {
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));//set с 1 значением

        userRepo.save(user);
        return true;
    }



  public boolean maxCount(User currentuser) {
      List <Video> s = videoRepository.findAllByAuthor(currentuser);
      Role arr[]=currentuser.getRoles().toArray(new Role[currentuser.getRoles().size()]);
      if (s.size() < arr[currentuser.getRoles().size()-1].getI())
            return true;
        else
            return false;
    }
}


