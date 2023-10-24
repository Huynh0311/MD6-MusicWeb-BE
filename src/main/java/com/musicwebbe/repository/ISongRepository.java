package com.musicwebbe.repository;

import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongRepository extends JpaRepository<Song, Integer> {

    @Query(nativeQuery = true, value = "select s.* from song s where s.id=:id")
    Song findSongByIDHQL(@Param("id") int id);

    @Query(nativeQuery = true, value = "select s.* from song s where s.genres_id=:id")
    List<Song> findAllSongByGenresID(@Param("id") int id);

    @Query(value = "SELECT s FROM Song s ORDER BY s.plays DESC ")
    List<Song> findTop5ByPlaysDesc();

    @Query(nativeQuery = true, value = "select s.* from song s inner join account a on s.account_id = a.id where s.name_song like %:name% order by  case when a.is_auth = true then 0 else 1 end;")
    List<Song> findListSongByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "select s.* from song s inner join account a on s.account_id = a.id where s.name_singer like %:name% order by  case when a.is_auth = true then 0 else 1 end;")
    List<Song> findListSongByNameSinger(@Param("name") String name);

    @Query(nativeQuery = true, value = "select pl.name_playlist,s.* from playlist pl inner join playlist_song pls on pl.id = pls.playlist_id inner join song s on pls.song_id=s.id inner join account a on pl.account_id = a.id where pl.name_playlist like %:name% and pl.id = :id order by  case when a.is_auth = true then 0 else 1 end;")
    List<Song> findListSongByPlaylistName(@Param("name") String name, @Param("id") int id);

    void deleteById(int id);
    @Query(nativeQuery = true,value ="SELECT account_id" +
            " FROM song" +
            " WHERE id = :id")
    int getAccountBySong(@Param("id") int id);

    List<Song> getAllByAccount_Id(int id);

    @Query(value = "SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Song s WHERE s.account.id = :accountId and s.id=:songId")
    boolean isSongOwnedByLoggedInAccount(@Param("songId") int songId, @Param("accountId") int accountId);
}