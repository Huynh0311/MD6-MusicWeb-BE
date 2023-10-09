package com.musicwebbe.service;

import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISongService extends IService<Song> {
    Song findSongByIDHQL (int id);
    List<Song> findAllSongByGenresID(int id);
}
