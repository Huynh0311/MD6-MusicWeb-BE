package com.musicwebbe.service.impl;

import com.musicwebbe.model.Singer;
import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;
import com.musicwebbe.repository.*;

import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService implements ISongService {
    @Autowired
    ISongRepository iSongRepository;

    @Autowired
    ILikesRepository likesRepository;

    @Autowired
    ISingerRepository isingerRepository;

    @Autowired
    ISingerSongRepository iSingerSongRepository;


    @Override
    public Song save(Song song) {
        return iSongRepository.save(song);
    }


    @Override
    public Song edit(Song song) {
        return iSongRepository.save(song);
    }

    @Override
    public Song findById(int id) {
        return iSongRepository.findById(id).get();
    }

    @Override
    public List<Song> getAll() {
        return iSongRepository.findAll();
    }

    @Override
    public Song findSongByIDHQL(int id) {
        return iSongRepository.findSongByIDHQL(id);
    }

    @Override
    public void delete(int id) {
        likesRepository.deleteAllBySong(iSongRepository.findById(id).get());
        iSongRepository.deleteById(id);

    }

    @Override
    public List<SongDTO2> getAllSong() {
        List<Song> songs = iSongRepository.findAll();
        return convertToSongDTOList(songs);
    }

    private List<SongDTO2> convertToSongDTOList(List<Song> songs) {
        return songs.stream()
                .map(song -> {
                    SongDTO2 songDTO2 = new SongDTO2();
                    songDTO2.setId(song.getId());
                    songDTO2.setNameSong(song.getNameSong());
                    songDTO2.setImgSong(song.getImgSong());
                    songDTO2.setPlays(song.getPlays());
                    songDTO2.setGenres(song.getGenres());
                    songDTO2.setTimeCreate(song.getTimeCreate());
                    songDTO2.setPathSong(song.getPathSong());
                    songDTO2.setDescription(song.getDescription());
                    songDTO2.setLikeQuantity(likesRepository.getLikeQuantity(song.getId()));
                    songDTO2.setSingers(isingerRepository.getSinger(song.getId()));
                    return songDTO2;
                })
                .collect(Collectors.toList());
    }


    @Override
    public SongDTO2 getaSong(int id) {
        Optional<Song>songOptional = iSongRepository.findById(id);
        Song song = songOptional.get();
        int likeQuantity = likesRepository.getLikeQuantity(id);
        List<String> singers = isingerRepository.getSinger(id);
        return new SongDTO2(song, likeQuantity, singers);
    }

    @Override
    public SongDTO2 editaSong(SongDTO2 songDTO2) {

        Song existingSong = iSongRepository.findById(songDTO2.getId()).get();

        existingSong.setId(songDTO2.getId());
        existingSong.setNameSong(songDTO2.getNameSong());
        existingSong.setImgSong(songDTO2.getImgSong());
        existingSong.setPlays(songDTO2.getPlays());
        existingSong.setGenres(songDTO2.getGenres());
        existingSong.setTimeCreate(songDTO2.getTimeCreate());
        existingSong.setPathSong(songDTO2.getPathSong());
        existingSong.setDescription(songDTO2.getDescription());
        Song savedSong = iSongRepository.save(existingSong);

        isingerRepository.deleteSingerBySongId(songDTO2.getId());
        iSingerSongRepository.deleteBySongId(songDTO2.getId());

        List<String> singers = isingerRepository.getSinger(songDTO2.getId());

        return new SongDTO2(savedSong, likesRepository.getLikeQuantity(songDTO2.getId()),singers);
    }
}
