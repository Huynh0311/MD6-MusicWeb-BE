package com.musicwebbe.repository;

import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface IPlaylistSongRepository extends JpaRepository<PlaylistSong,Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "delete FROM musicweb_md6.playlist_song WHERE song_id LIKE %:idsong%;")
    void deleteBySongId(int idsong);
}
