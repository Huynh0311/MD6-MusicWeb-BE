package com.musicwebbe.repository;

import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Song,Integer> {

    void deleteById(int id);



}
