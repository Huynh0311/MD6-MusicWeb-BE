package com.musicwebbe.repository;

import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongRepository extends JpaRepository<Song,Integer> {

    @Query(nativeQuery = true,value ="select s.* from song s where s.id=:id")
    Song findSongByIDHQL(@Param("id") int id);

    @Query(nativeQuery = true,value = "select s.* from song s where s.genres_id=:id")
    List<Song> findAllSongByGenresID(@Param("id")int id);
    @Query("SELECT s FROM Song s ORDER BY s.plays DESC")
    List<Song> findTop5ByPlaysDesc();

    void deleteById(int id);

    List<Song> getAllByAccount_Id(int id);

}
