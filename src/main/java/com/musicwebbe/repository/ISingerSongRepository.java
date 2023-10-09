package com.musicwebbe.repository;

import com.musicwebbe.model.Singer;
import com.musicwebbe.model.SingerSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface ISingerSongRepository extends JpaRepository<SingerSong,Integer> {
    void deleteBySong(int id);

    @Modifying
    @Query(nativeQuery = true, value = "SELECT singer_id FROM musicweb_md6.singer_song WHERE song_id LIKE %:idsong%;")
    int findSingerIdBySongId(int idsong);

    @Modifying
    @Query(nativeQuery = true, value = "delete FROM musicweb_md6.singer_song WHERE song_id LIKE %:idsong%;")
    void deleteBySongId(int idsong);
}
