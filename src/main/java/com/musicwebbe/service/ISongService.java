package com.musicwebbe.service;

import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.model.Song;
import java.util.List;

public interface ISongService extends IService<Song> {

    void delete(int id);

    List<SongDTO2> getAllSong();

    SongDTO2 getaSong(int id);

    SongDTO2 editaSong(SongDTO2 songDTO2);

    Song findSongByIDHQL(int id);

}
