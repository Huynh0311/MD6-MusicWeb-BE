package com.musicwebbe.service;

import com.musicwebbe.model.dto.SongDTO2;

import java.util.List;

public interface ISongService {

    void delete(int id);

    List<SongDTO2> getAllSong();

    SongDTO2 getaSong(int id);

    SongDTO2 editaSong(SongDTO2 songDTO2);

//    SongDTO2 editaSong(Song song, List<String> singer);
}
