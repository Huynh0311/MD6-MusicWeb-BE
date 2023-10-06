package com.musicwebbe.repository;

import com.musicwebbe.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISingerRepository extends JpaRepository<Singer,Integer> {

    @Query(nativeQuery = true, value = "SELECT name_singer FROM musicweb_md6.singer inner join musicweb_md6.singer_song on singer_song.singer_id = singer.id inner join song on song.id = singer_song.song_id where song.id like %:idsong%;")
    List<String> getSinger(@Param("idsong")  int idsong);
}
