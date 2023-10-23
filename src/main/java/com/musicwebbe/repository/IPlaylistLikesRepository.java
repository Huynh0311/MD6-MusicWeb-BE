package com.musicwebbe.repository;

import com.musicwebbe.model.PlaylistLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPlaylistLikesRepository extends JpaRepository<PlaylistLikes,Integer> {
    @Query(nativeQuery = true,value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN '1' ELSE '0' END AS UNSIGNED) AS result FROM playlist_likes WHERE playlist_id = :idPlaylist and account_id=:idAccount")
    Integer isLiked(@Param("idPlaylist")int idPlaylist, @Param("idAccount")int idAccount);

//    @Query(nativeQuery = true,value = "select count(*) from likes where likes.song_id=:id")
    Integer countByPlaylistId(int id);

//    @Query(nativeQuery = true,value = "delete from likes where likes.song_id=:idSong and likes.account_id=:idAccount")
    @Modifying
    void deleteByPlaylistIdAndAccountId(int playlistID,int idAccount);


}
