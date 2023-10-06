package com.musicwebbe.service;

import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;

import java.util.List;
import java.util.Optional;

public interface ISingerSongService {

    SingerSong save(SingerSong singerSong);

    boolean delete(int id);


    Optional<SingerSong> findById(int id);

    List<SingerSong> getAll();

    List<Song> getAllSongsBySingerId(int singerId);
}
