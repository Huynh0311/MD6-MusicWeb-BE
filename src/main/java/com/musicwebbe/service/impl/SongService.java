package com.musicwebbe.service.impl;

import com.musicwebbe.model.Song;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    @Autowired
    ISongRepository iSongRepository;

    @Override
    public Song save(Song song) {
        return iSongRepository.save(song);
    }

    @Override
    public Song edit(Song song) {
        return iSongRepository.save(song);
    }

    @Override
    public void delete(int id) {
        iSongRepository.deleteById(id);
    }

    @Override
    public Song findById(int id) {
        return iSongRepository.findById(id).get();
    }

    @Override
    public List<Song> getAll() {
        return iSongRepository.findAll();
    }

    @Override
    public Song findSongByIDHQL(int id) {
        return iSongRepository.findSongByIDHQL(id);
    }
    @Override
    public List<Song> findTop5ByPlaysDesc() {
        return iSongRepository.findTop5ByPlaysDesc();
    }
}
