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
    @Query(value = "SELECT s FROM Song s ORDER BY s.plays DESC ")
    List<Song> findTop5ByPlaysDesc();
    @Query(value = "SELECT * FROM Song" +
            " WHERE name_song  LIKE CONCAT('%', :search , '%' )",nativeQuery = true)
    List<Song> searchSongByNameSong(@Param("search") String search);

    void deleteById(int id);


}
