package com.musicwebbe.repository;

import com.musicwebbe.model.Singer;
import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISingerSongRepository extends JpaRepository<SingerSong,Integer> {
    @Query("SELECT ss.song FROM SingerSong ss WHERE ss.singer.id = :singerId")
    List<Song> findAllSongsBySingerId(@Param("singerId") int singerId);

}
