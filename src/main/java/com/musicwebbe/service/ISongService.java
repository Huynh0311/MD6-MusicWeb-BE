package com.musicwebbe.service;

import com.musicwebbe.model.Singer;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO;

import java.util.List;

public interface ISongService {

    void delete(int id);

    List<SongDTO> getAllSong();

    SongDTO getaSong(int id);

    SongDTO editaSong(SongDTO songDTO);

//    SongDTO editaSong(Song song, List<String> singer);
}
