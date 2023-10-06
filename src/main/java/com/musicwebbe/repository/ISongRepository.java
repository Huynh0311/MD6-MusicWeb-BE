package com.musicwebbe.repository;

import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISongRepository extends JpaRepository<Song,Integer> {
    @Query("SELECT s FROM Song s ORDER BY s.plays DESC")
    List<Song> findTop5ByPlaysDesc();
}
