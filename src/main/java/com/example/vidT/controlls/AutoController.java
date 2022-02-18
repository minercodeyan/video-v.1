package com.example.vidT.controlls;

import com.example.vidT.CodeGeneraror;
import com.example.vidT.Service.EmailSenderService;
import com.example.vidT.Service.UserService;
import com.example.vidT.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AutoController {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSenderService service;

    @GetMapping("/reg")
    public String reg(@ModelAttribute("user") User user) {
        return "reg";
    }


    @PostMapping("/reg")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model mod) {

        if (bindingResult.hasErrors()) {
            return "reg";
        } else {
            if (!user.getPassword().equals(user.getPassword2())) {
                mod.addAttribute("massage2", "пароли не совпадают");
                return "reg";
            }
            if (userService.addUser(user) == 1) {
                mod.addAttribute("massage1", "такое имя уже есть");
                return "reg";
            }
            if (userService.addUser(user) == 2) {
                mod.addAttribute("massage3", "такой email уже есть");
                return "reg";
            }
        }

        String code = CodeGeneraror.generate();
        service.sendSimpleEmail(user.getEmail(),code, "confirm");//spring uniccode
        mod.addAttribute("secret", code);
        mod.addAttribute("userId", user.getId());
        return "confirmemail";
    }
}
