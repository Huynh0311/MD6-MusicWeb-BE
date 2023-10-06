package com.musicwebbe.service;

import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;

import java.util.List;

public interface ISingerSongService {





    List<SingerSong> getAll();

    List<Song> findAllSongsBySimilarSingerName(String singerName);
}
