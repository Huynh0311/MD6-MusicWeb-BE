package com.musicwebbe.service;

import com.musicwebbe.model.PlaylistSong;
import com.musicwebbe.model.Song;

import java.util.List;

public interface IPlaylistSongService {
    int countSong(Integer id);
    List<Song> findAllByPlaylist(Integer id);

    void save(PlaylistSong playlistSong);
}
