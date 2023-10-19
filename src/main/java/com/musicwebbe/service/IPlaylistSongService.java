package com.musicwebbe.service;

import com.musicwebbe.model.PlaylistSong;
import com.musicwebbe.model.Song;

import java.util.List;

public interface IPlaylistSongService {
    int countSong(int id);
    List<Song> findAllByPlaylist(int id);
}
