package com.musicwebbe.repository;

import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaylistSongRepository extends JpaRepository<PlaylistSong,Integer> {

}
