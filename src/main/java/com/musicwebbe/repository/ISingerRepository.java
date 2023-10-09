package com.musicwebbe.repository;

import com.musicwebbe.model.Role;
import com.musicwebbe.model.Singer;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISingerRepository extends JpaRepository<Singer,Integer> {

    @Query(nativeQuery = true,value ="select sg.* from Singer sg inner join singer_song ss on ss.singer_id=sg.id inner join song s on ss.song_id=s.id where s.id=id;")
    Singer findSingerBySongID(@Param("id") int id);

    @Query(nativeQuery = true,value ="select sg.* from Singer sg inner join singer_song ss on ss.singer_id=sg.id inner join song s on ss.song_id=s.id where s.id like %:id")
    List<Singer> findListSingerBySongID(@Param("id") int id);
}
