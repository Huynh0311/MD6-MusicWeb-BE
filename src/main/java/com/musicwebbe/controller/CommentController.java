package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Comment;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.AccountDTO2;
import com.musicwebbe.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    ICommentService iCommentService;
    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        LocalDateTime commentTime = LocalDateTime.now();
        comment.setTimeComment(LocalDate.from(commentTime));
        return new  ResponseEntity<> (iCommentService.save(comment), HttpStatus.OK);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<Comment>>getAllCommentBySongID(@PathVariable int id){
        return new ResponseEntity<>(iCommentService.findCommentBySongID(id),HttpStatus.OK);
    }
}
