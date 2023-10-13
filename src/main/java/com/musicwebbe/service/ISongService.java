package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongFavorite;

import java.util.List;


public interface ISongService extends IService<Song> {

    List<Song> getAll();

    List<Song> findTop5ByPlaysDesc();

    void delete(int id);

    List<SongDTO2> getAllSong();

    SongDTO2 getaSong(int id);

    SongDTO2 editaSong(SongDTO2 songDTO2);

    void deleteaSong(int id);

    Song addSong(Account account, Song song);

    SongDTO findSongById(int id);

    List<SongDTO> getAllSongByGenresID(Song song);

    List<SongFavorite> getAllFavoritesByUser(String username);
}
