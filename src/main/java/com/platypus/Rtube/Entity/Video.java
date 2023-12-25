package com.platypus.Rtube.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    private String name;
    private String description;
    private LocalDateTime date;

    public Video(User author, String filename, String name, String description) {
        this.author = author;
        this.filename = filename;
        this.name = name;
        this.description = description;
        date = LocalDateTime.now();
    }
}
