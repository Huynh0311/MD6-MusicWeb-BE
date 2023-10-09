package com.musicwebbe.service.impl;

import com.musicwebbe.model.Comment;
import com.musicwebbe.repository.ICommentRepository;
import com.musicwebbe.service.ICommentService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepository iCommentRepository;
    @Override
    public Comment save(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        iCommentRepository.deleteById(id);
    }

    @Override
    public Comment findById(int id) {
        return iCommentRepository.findById(id).get();
    }

    @Override
    public List<Comment> getAll() {
        return iCommentRepository.findAll();
    }


    @Override
    public List<Comment> findCommentBySongID(int id) {
        return iCommentRepository.findCommentBySongID(id);
    }
}
