package com.musicwebbe.service.impl;

import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;
import com.musicwebbe.repository.ISingerSongRepository;
import com.musicwebbe.service.ISingerSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerSongService implements ISingerSongService {

    @Autowired
    ISingerSongRepository iSingerSongRepository;




    @Override
    public List<SingerSong> getAll() {
        return iSingerSongRepository.findAll();
    }

    @Override
    public List<Song> findAllSongsBySimilarSingerName(String singerName) {
        return iSingerSongRepository.findAllSongsBySimilarSingerName(singerName);
    }

}
