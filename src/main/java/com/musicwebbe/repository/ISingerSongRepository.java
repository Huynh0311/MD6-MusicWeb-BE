package com.musicwebbe.repository;

import com.musicwebbe.model.Singer;
import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public interface ISingerSongRepository extends JpaRepository<SingerSong,Integer> {
    @Query("SELECT ss.song FROM SingerSong ss WHERE ss.singer.nameSinger LIKE %:singerName%")
    List<Song> findAllSongsBySimilarSingerName(@Param("singerName") String singerName);
    @Modifying
    @Query(nativeQuery = true, value = "SELECT singer_id FROM musicweb_md6.singer_song WHERE song_id LIKE %:idsong%;")
    int findSingerIdBySongId(int idsong);

    @Modifying
    @Query(nativeQuery = true, value = "delete FROM musicweb_md6.singer_song WHERE song_id LIKE %:idsong%;")
    void deleteBySongId(int idsong);
}
