package com.platypus.Rtube.repository;

import com.platypus.Rtube.Entity.User;
import com.platypus.Rtube.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video, Integer> {
    List<Video> findAllByAuthor(User author);
    Video findByFilename(String filename);
}
