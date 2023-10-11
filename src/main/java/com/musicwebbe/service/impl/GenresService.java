package com.musicwebbe.service.impl;

import com.musicwebbe.model.Genres;
import com.musicwebbe.repository.IGenresRepository;
import com.musicwebbe.service.IGenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresService implements IGenresService {
    @Autowired
    IGenresRepository iGenresRepository;
    @Override
    public Genres save(Genres genres) {
        return iGenresRepository.save(genres);
    }

    @Override
    public Genres edit(Genres genres) {
        return iGenresRepository.save(genres);
    }

    @Override
    public void delete(int id) {
        iGenresRepository.deleteById(id);
    }

    @Override
    public Genres findById(int id) {
        return iGenresRepository.findById(id).get();
    }

    @Override
    public List<Genres> getAll() {
        return iGenresRepository.findAll();
    }
}
