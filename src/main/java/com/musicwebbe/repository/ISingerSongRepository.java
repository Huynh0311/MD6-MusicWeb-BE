package com.musicwebbe.repository;

import com.musicwebbe.model.Singer;
import com.musicwebbe.model.SingerSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISingerSongRepository extends JpaRepository<SingerSong,Integer> {

}
