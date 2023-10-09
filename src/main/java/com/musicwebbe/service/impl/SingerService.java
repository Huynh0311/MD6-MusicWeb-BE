package com.musicwebbe.service.impl;

import com.musicwebbe.model.Singer;
import com.musicwebbe.repository.ISingerRepository;
import com.musicwebbe.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerService implements ISingerService {
    @Autowired
    ISingerRepository iSingerRepository;
    @Override
    public Singer save(Singer singer) {
        return iSingerRepository.save(singer);
    }

    @Override
    public Singer edit(Singer singer) {
        return iSingerRepository.save(singer);
    }

    @Override
    public void delete(int id) {
        iSingerRepository.deleteById(id);
    }

    @Override
    public Singer findById(int id) {
        return iSingerRepository.findById(id).get();
    }

    @Override
    public List<Singer> getAll() {
        return null;
    }

    @Override
    public Singer findSingerBySongID(int id) {
        return iSingerRepository.findSingerBySongID(id);
    }

    @Override
    public List<Singer> findListSingerBySongID(int id) {
        return iSingerRepository.findListSingerBySongID(id);
    }
}
