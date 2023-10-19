package com.musicwebbe.repository;

import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface ILikesRepository extends JpaRepository<Likes,Integer> {
    @Query(nativeQuery = true, value = "SELECT count(account_id) FROM musicweb_md6.likes where likes.song_id LIKE %:idsong%;")
    int getLikeQuantity(@Param("idsong")  int idsong);

    void deleteAllBySong(Song song);
    @Query(nativeQuery = true,value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN '1' ELSE '0' END AS UNSIGNED) AS result FROM likes WHERE song_id = :idSong and account_id=:idAccount")
    int isLiked(@Param("idSong")int idSong,@Param("idAccount")int idAccount);

    @Query(nativeQuery = true,value = "select count(*) from likes where likes.song_id=:id")
    int getLikeQuantity2(@Param("id")int id);
    @Query(nativeQuery = true,value = "delete from likes where likes.song_id=:idSong and likes.account_id=:idAccount")
    @Modifying
    void removeLikeBySongIDAndAccountID(@Param("idSong")int idSong,@Param("idAccount")int idAccount);
}
