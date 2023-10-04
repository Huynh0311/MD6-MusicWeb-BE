package com.musicwebbe.repository;

import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaylistRepository extends JpaRepository<Playlist,Integer> {

}
