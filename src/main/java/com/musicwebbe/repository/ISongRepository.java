package com.musicwebbe.repository;

import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISongRepository extends JpaRepository<Song,Integer> {
    @Query(nativeQuery = true,value ="select s.* from song s inner join singer_song ss on ss.song_id=s.id where s.id=144 group by s.id;")
    Song findSongByIDHQL(@Param("id") int id);


    void deleteById(int id);



}
