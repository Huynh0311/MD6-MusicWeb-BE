package com.musicwebbe.repository;

import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongRepository extends JpaRepository<Song,Integer> {
    @Query(nativeQuery = true,value ="select s.* from song s inner join singer_song ss on ss.song_id=s.id where s.id=:id group by s.id;")
    Song findSongByIDHQL(@Param("id") int id);

    @Query(nativeQuery = true,value = "select s.* from song s inner join genres g on s.genres_id= g.id inner join singer_song ss on s.id=ss.song_id inner join singer sg on ss.singer_id=sg.id where g.id=:id group by s.id")
    List<Song> findAllSongByGenresID(@Param("id")int id);
    @Query("SELECT s FROM Song s ORDER BY s.plays DESC")
    List<Song> findTop5ByPlaysDesc();
}
