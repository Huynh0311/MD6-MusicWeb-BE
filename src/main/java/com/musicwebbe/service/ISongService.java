package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongFavorite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ISongService extends IService<Song> {

    List<Song> getAll();

    List<SongDTO> findTop5ByPlaysDesc(Account account);

    void delete(int id);

    List<SongDTO2> getAllSong();

    SongDTO2 getaSong(int id);

    SongDTO2 editaSong(SongDTO2 songDTO2);

    void deleteaSong(int id);

    Song addSong(Account account, Song song);
    SongDTO findSongById(int id);

    List<SongDTO> getAllSongByGenresID(Song song);

    List<SongDTO> findListSongByName(String name,Account account);

    int getAccountBySong(int id);
    List<SongDTO> findListSongByNameSinger(String name,Account account);
    List<List<SongDTO>> findListSongByPlaylist(String name,Account account);

    public List<Song> getAllSongByAccountId(int id);
    long getTotalSongs();
    List<SongFavorite> getAllFavoritesByUser(String username);

    boolean isSongOwnedByLoggedInAccount (int id,Account account);
}
