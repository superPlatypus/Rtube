package com.platypus.Rtube.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    private Video video;

    private String text;
    private LocalDateTime date;

    public Comment(User author, Video video, String text) {
        this.author = author;
        this.video = video;
        this.text = text;
        this.date = LocalDateTime.now();
    }
}