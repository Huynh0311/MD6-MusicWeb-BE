package com.musicwebbe.repository;

import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlaylistRepository extends JpaRepository<Playlist,Integer> {
    @Query(nativeQuery = true,value ="SELECT account_id" +
            " FROM playlist" +
            " WHERE id = :id")
    int getAccount(@Param("id") int id);
    @Query(nativeQuery = true,value = "select pl.* from playlist pl where pl.name_playlist like %:name%")
    List<Playlist> findAllPlaylistByNamePlaylist(@Param("name") String name);

}
