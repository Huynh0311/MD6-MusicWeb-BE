package com.musicwebbe.repository;

import com.musicwebbe.model.Comment;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM comment c WHERE c.song_id = :id order by c.id DESC")
    List<Comment> findCommentBySongID(@Param("id") int id);
}
