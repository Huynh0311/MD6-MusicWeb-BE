package com.musicwebbe.repository;

import com.musicwebbe.model.Comment;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ICommentRepository extends JpaRepository<Comment,Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM comment c WHERE c.song_id = :id order by c.id DESC")
    List<Comment> findCommentBySongID(@Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "delete FROM musicweb_md6.comment WHERE song_id LIKE %:idsong%;")
    void deleteBySongId(int idsong);
}
