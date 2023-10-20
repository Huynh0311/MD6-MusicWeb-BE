package com.musicwebbe.service.impl;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.repository.ILikesRepository;
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
    @Autowired
    private ILikesRepository ilikesRepository;

    @Override
    public int countSong(int id) {
        return iPlaylistSongRepository.countSong(id);
    }

//    @Override
//    public List<Song> findAllByPlaylist(int id) {
//        List<Song> listSong = new ArrayList<>();
//        for (int i:iPlaylistSongRepository.findAllByPlaylist(id)) {
//            listSong.add(iSongRepository.findById(i).get());
//        }
//        return listSong;
//    }

    @Override
    public List<SongDTO> findAllByPlaylist(int id, Account account) {
        List<SongDTO> listSong = new ArrayList<>();
        for (int i:iPlaylistSongRepository.findAllByPlaylist(id)) {
            Song song = iSongRepository.findById(i).get();
            int isLiked = ilikesRepository.isLiked(song.getId(), account.getId());
            SongDTO songDTO = new SongDTO(song.getId(),song.getNameSong(),song.getImgSong(),song.getPathSong(),song.getAccount().getId(),song.getDescription(),isLiked);
            listSong.add(songDTO);
        }
        return listSong;
    }
}
