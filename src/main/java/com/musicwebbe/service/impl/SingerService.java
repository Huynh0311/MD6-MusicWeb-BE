package com.musicwebbe.service.impl;

import com.musicwebbe.model.Singer;
import com.musicwebbe.repository.ISingerRepository;
import com.musicwebbe.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService implements ISingerService {
    @Autowired
    ISingerRepository iSingerRepository;

    @Override
    public Singer save(Singer singer) {
        return iSingerRepository.save(singer);
    }

    @Override
    public boolean delete(int id) {
        iSingerRepository.deleteById(id);
        return false;
    }

    @Override
    public Optional<Singer> findById(int id) {

        return iSingerRepository.findById(id);
    }

    @Override
    public List<Singer> getAll() {
        return iSingerRepository.findAll();
    }
}
