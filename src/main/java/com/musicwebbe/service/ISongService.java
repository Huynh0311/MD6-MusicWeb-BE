package com.musicwebbe.service;

import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {

    Song save(Song song);

    boolean delete(int id);


    Optional<Song> findById(int id);
    List<Song> getAll();

}
