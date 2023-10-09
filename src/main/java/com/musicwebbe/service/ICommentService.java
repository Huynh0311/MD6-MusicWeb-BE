package com.musicwebbe.service;

import com.musicwebbe.model.Comment;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    List<Comment> findCommentBySongID(int id);
}
