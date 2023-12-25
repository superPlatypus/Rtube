package com.platypus.Rtube.controller;

import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.repository.UserRepo;
import com.platypus.Rtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Model model){
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Error");
            return "registration";
        }

        return "redirect:/login";
    }
}