package com.platypus.Rtube.repository;

import com.platypus.Rtube.Entity.Comment;
import com.platypus.Rtube.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long>{

    List<Comment> findAllByVideo(Video video);
}
