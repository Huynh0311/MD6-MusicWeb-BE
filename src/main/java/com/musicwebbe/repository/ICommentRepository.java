package com.musicwebbe.repository;

import com.musicwebbe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {

}
