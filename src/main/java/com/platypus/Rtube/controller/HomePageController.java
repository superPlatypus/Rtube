package com.platypus.Rtube.controller;

import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.Entity.Video;
import com.platypus.Rtube.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomePageController {
    @Autowired
    VideoRepo videoRepo;

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("videos", videoRepo.findAll());
        return "home";
    }




}
