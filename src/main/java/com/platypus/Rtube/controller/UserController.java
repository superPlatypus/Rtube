package com.platypus.Rtube.controller;


import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.Entity.Video;
import com.platypus.Rtube.repository.UserRepo;
import com.platypus.Rtube.repository.VideoRepo;
import com.platypus.Rtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public String userPage(@PathVariable String username,
                           Model model,
                           @AuthenticationPrincipal User currentUser){
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("videos", videoRepo.findAllByAuthor(user));

//        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("currentUser", currentUser);

        return "user";
    }

    @GetMapping("{username}/subscribe")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable String username
    ) {
        User user = userRepo.findByUsername(username);
        userService.subscribe(currentUser, user);

        return "redirect:/user/" + user.getUsername();
    }

    @GetMapping("{username}/unsubscribe")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable String username
    ) {
        User user = userRepo.findByUsername(username);
        userService.unsubscribe(currentUser, user);

        return "redirect:/user/" + user.getUsername();
    }

    @GetMapping("{username}/{type}/list")
    public String userList(
            Model model,
            @PathVariable String username,
            @PathVariable String type
    ) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        return "subscriptions";
    }

    @GetMapping("/{username}/video/{filename}")
    public String video(@PathVariable String username,
                        @PathVariable String filename,
                        Model model){
        model.addAttribute("video", videoRepo.findByFilename(filename));
        return "video";
    }



}
