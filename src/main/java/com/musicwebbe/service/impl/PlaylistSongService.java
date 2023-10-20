package com.musicwebbe.service.impl;

import com.musicwebbe.model.PlaylistSong;
import com.musicwebbe.model.Song;
import com.musicwebbe.repository.IPlaylistSongRepository;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.IPlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistSongService implements IPlaylistSongService {
    @Autowired
    private IPlaylistSongRepository iPlaylistSongRepository;
    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public int countSong(Integer id) {
        return iPlaylistSongRepository.countSong(id);
    }

    @Override
    public List<Song> findAllByPlaylist(Integer id) {
        List<Song> listSong = new ArrayList<>();
        for (int i:iPlaylistSongRepository.findAllByPlaylist(id)) {
            listSong.add(iSongRepository.findById(i).get());
        }
        return listSong;
    }

    @Override
    public void save(PlaylistSong playlistSong) {
        iPlaylistSongRepository.save(playlistSong);
    }
}
