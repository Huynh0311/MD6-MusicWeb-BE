package com.musicwebbe.service.impl;


import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.repository.ILikesRepository;
import com.musicwebbe.repository.ISingerRepository;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService implements ISongService {

    @Autowired
    ISongRepository songRepository;

    @Autowired
    ILikesRepository likesRepository;

    @Autowired
    ISingerRepository singerRepository;

    @Override
    public void delete(int id) {
        likesRepository.deleteAllBySong(songRepository.findById(id).get());
        songRepository.deleteById(id);

    }

    @Override
    public List<SongDTO2> getAllSong() {
        List<Song> songs = songRepository.findAll();
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
                    songDTO2.setSingers(singerRepository.getSinger(song.getId()));
                    return songDTO2;
                })
                .collect(Collectors.toList());
    }


    @Override
    public SongDTO2 getaSong(int id) {
        Optional<Song>songOptional = songRepository.findById(id);
        Song song = songOptional.get();
        int likeQuantity = likesRepository.getLikeQuantity(id);
        List<String> singers = singerRepository.getSinger(id);
        return new SongDTO2(song, likeQuantity, singers);
    }

//    @Override
//    public SongDTO2 editaSong(SongDTO2 songDTO) {
//        Optional<Song>songOptional = songRepository.findById(songDTO.getId());
//        Song newSong = songRepository.save(songOptional.get());
//        List<String> singers = singerRepository.getSinger(songDTO.getId());
////        singerRepository.updateSingers(song.getId(), songDTO.getSingers())
//        return new SongDTO2(newSong, songDTO.getLikeQuantity(), songDTO.getSingers());
//    }


//    @Override
//    public SongDTO2 editaSong(SongDTO2 songDTO) {
//                    SongDTO2 newsongDTO = new SongDTO2();
//
//                    newsongDTO.setId(songDTO.getId());
//                    newsongDTO.setNameSong(songDTO.getNameSong());
//                    newsongDTO.setImgSong(songDTO.getImgSong());
//                    newsongDTO.setPlays(songDTO.getPlays());
//                    newsongDTO.setGenres(songDTO.getGenres());
//                    newsongDTO.setTimeCreate(songDTO.getTimeCreate());
//                    newsongDTO.setPathSong(songDTO.getPathSong());
//                    newsongDTO.setDescription(songDTO.getDescription());
//                    newsongDTO.setLikeQuantity(likesRepository.getLikeQuantity(songDTO.getId()));
//                    newsongDTO.setSingers(singerRepository.getSinger(songDTO.getId()));
//
//                    return songDTO;
//                }

    @Override
    public SongDTO2 editaSong(SongDTO2 songDTO2) {

        Song existingSong = songRepository.findById(songDTO2.getId())
                .orElseThrow(() -> new RuntimeException("Song not found"));

        existingSong.setId(songDTO2.getId());
        existingSong.setNameSong(songDTO2.getNameSong());
        existingSong.setImgSong(songDTO2.getImgSong());
        existingSong.setPlays(songDTO2.getPlays());
        existingSong.setGenres(songDTO2.getGenres());
        existingSong.setTimeCreate(songDTO2.getTimeCreate());
        existingSong.setPathSong(songDTO2.getPathSong());
        existingSong.setDescription(songDTO2.getDescription());
        Song savedSong = songRepository.save(existingSong);
        List<String> singers = singerRepository.getSinger(songDTO2.getId());
        return new SongDTO2(savedSong, likesRepository.getLikeQuantity(songDTO2.getId()),singers);
    }
}
