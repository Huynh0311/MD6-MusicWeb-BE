package com.musicwebbe.repository;

import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface ILikesRepository extends JpaRepository<Likes,Integer> {
    @Query(nativeQuery = true, value = "SELECT count(account_id) FROM musicweb_md6.likes where likes.song_id LIKE %:idsong%;")
    int getLikeQuantity(@Param("idsong")  int idsong);

    void deleteAllBySong(Song song);

}
