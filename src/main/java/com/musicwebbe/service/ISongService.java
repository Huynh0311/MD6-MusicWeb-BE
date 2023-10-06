package com.musicwebbe.service;

import com.musicwebbe.model.Song;

import java.util.List;

public interface ISongService {

    List<Song> getAll();
    List<Song> findTop5ByPlaysDesc();


}
