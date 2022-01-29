package com.example.vidT.controlls;

import com.example.vidT.repositories.UserRepo;
import com.example.vidT.models.Role;
import com.example.vidT.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AutoController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/reg")
    public String reg(){
       return "reg";
    }


    @PostMapping("/reg")
    public String addUser(User user, Model mod){
     User userFromDb = userRepo.findByUsername(user.getUsername());
      if(userFromDb != null){
          mod.addAttribute("massage","такое имя уже есть");
        return "reg";
      }
       user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));//set с 1 значением
        userRepo.save(user);
        return "redirect:/login";
    }
}
