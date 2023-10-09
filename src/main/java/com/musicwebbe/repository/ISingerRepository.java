package com.musicwebbe.repository;

import com.musicwebbe.model.Role;
import com.musicwebbe.model.Singer;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ISingerRepository extends JpaRepository<Singer,Integer> {

    @Query(nativeQuery = true, value = "SELECT name_singer FROM musicweb_md6.singer inner join musicweb_md6.singer_song on singer_song.singer_id = singer.id inner join song on song.id = singer_song.song_id where song.id like %:idsong%;")
    List<String> getSinger(@Param("idsong")  int idsong);
    @Query(nativeQuery = true,value ="select sg.* from Singer sg inner join singer_song ss on ss.singer_id=sg.id inner join song s on ss.song_id=s.id where s.id=id;")
    Singer findSingerBySongID(@Param("id") int id);


    @Query(nativeQuery = true, value = "SELECT singer.* FROM musicweb_md6.singer inner join musicweb_md6.singer_song on singer_song.singer_id = singer.id inner join song on song.id = singer_song.song_id where song.id like %:idsong%;")
    List<Singer> getAllSinger(@Param("idsong")  int idsong);


    @Modifying
    @Query(nativeQuery = true, value = "delete singer.* FROM musicweb_md6.singer inner join musicweb_md6.singer_song on singer_song.singer_id = singer.id inner join song on song.id = singer_song.song_id where song.id like %:idsong%;")
    List<Singer> deleteSingerBySongId(@Param("idsong")  int idsong);
}
