package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.PlaylistSong;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO;

import java.util.List;

public interface IPlaylistSongService {
    int countSong(int id);
    List<SongDTO> findAllByPlaylist(int id, Account account);

    void save(PlaylistSong playlistSong);
}
