package com.platypus.Rtube.controller;

import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.Entity.Video;
import com.platypus.Rtube.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/addvideo")
public class ProfileController {


    @Autowired
    VideoRepo videoRepo;
    @Value("${upload.path}")
    private String uploadPath;

//    @GetMapping
//    public String profile(@AuthenticationPrincipal User user,
//                          Model model){
//        model.addAttribute("user", user);
//        return "profile";
//    }

    @PostMapping()
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            Video video = new Video(user,resultFilename, name, description);

            videoRepo.save(video);
        }



        return "redirect:/user/" + user.getUsername();
    }
}
