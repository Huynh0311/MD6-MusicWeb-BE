package com.musicwebbe.repository;

import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.PlaylistSong;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlaylistSongRepository extends JpaRepository<PlaylistSong,Integer> {
    @Query(nativeQuery = true,value ="SELECT COUNT(*)" +
            " FROM playlist_song" +
            " WHERE playlist_id = :id")
    int countSong(@Param("id") int id);
    @Query(nativeQuery = true,value ="SELECT song_id" +
            " FROM playlist_song" +
            " WHERE playlist_id = :id")
    List<Integer> findAllByPlaylist(@Param("id") int id);
}
