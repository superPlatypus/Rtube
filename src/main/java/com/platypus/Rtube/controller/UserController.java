package com.platypus.Rtube.controller;


import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.Entity.Video;
import com.platypus.Rtube.repository.UserRepo;
import com.platypus.Rtube.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    VideoRepo videoRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/{username}")
    public String userPage(@PathVariable String username,
                           Model model){
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("videos", videoRepo.findAllByAuthor(user));
        return "user";
    }

    @GetMapping("/{username}/video/{filename}")
    public String video(@PathVariable String username,
                        @PathVariable String filename,
                        Model model){
        model.addAttribute("video", videoRepo.findByFilename(filename));
        return "video";
    }

}
