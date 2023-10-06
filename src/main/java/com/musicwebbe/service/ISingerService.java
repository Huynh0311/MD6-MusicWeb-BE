package com.musicwebbe.service;

import com.musicwebbe.model.Singer;

import java.util.List;
import java.util.Optional;

public interface ISingerService {

    Singer save(Singer singer);

    boolean delete(int id);


    Optional<Singer> findById(int id);

    List<Singer> getAll();


}
