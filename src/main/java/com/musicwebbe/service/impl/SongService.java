package com.musicwebbe.service.impl;

import com.musicwebbe.model.Song;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    @Autowired
    ISongRepository iSongRepository;

    @Override
    public Song save(Song song) {
        return iSongRepository.save(song);

    }

    @Override
    public boolean delete(int id) {
        iSongRepository.deleteById(id);
        return false;
    }

    @Override
    public Optional<Song> findById(int id) {
        return iSongRepository.findById(id);
    }

    @Override
    public List<Song> getAll() {
        return iSongRepository.findAll();
    }

    @Override
    public List<Song> findTop5ByPlaysDesc() {
        return iSongRepository.findTop5ByPlaysDesc();
    }

}
