package com.platypus.Rtube.controller;

import com.platypus.Rtube.Entity.Comment;
import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.Entity.Video;
import com.platypus.Rtube.repository.CommentRepo;
import com.platypus.Rtube.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    VideoRepo videoRepo;


    @PostMapping("/comment")
    public String comment(@AuthenticationPrincipal User user,
                          @RequestParam String text,
                          @RequestParam String filename,
                          Model model){
        Video video = videoRepo.findByFilename(filename);
        Comment comment = new Comment(user, video,text);
        commentRepo.save(comment);
        model.addAttribute("comments", commentRepo.findAllByVideo(video));
        return "redirect:/user/" + video.getAuthor().getUsername() + "/video/" + video.getFilename();
    }
}
