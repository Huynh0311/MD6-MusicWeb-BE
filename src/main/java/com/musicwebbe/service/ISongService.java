package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.model.Song;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

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
    public SongDTO findSongById(int id);

    public List<SongDTO> getAllSongByGenresID(Song song);

    List<SongDTO> findListSongByName(String name,Account account);

    List<SongDTO> findListSongByNameSinger(String name,Account account);
    List<List<SongDTO>> findListSongByPlaylist(String name,Account account);

    public List<Song> getAllSongByAccountId(int id);
    long getTotalSongs();
}
