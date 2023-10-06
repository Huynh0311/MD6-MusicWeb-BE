package com.musicwebbe.service.impl;


import com.musicwebbe.model.Singer;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO;
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
    public List<SongDTO> getAllSong() {
        List<Song> songs = songRepository.findAll();
        return convertToSongDTOList(songs);
    }

    private List<SongDTO> convertToSongDTOList(List<Song> songs) {
        return songs.stream()
                .map(song -> {
                    SongDTO songDTO = new SongDTO();
                    songDTO.setId(song.getId());
                    songDTO.setNameSong(song.getNameSong());
                    songDTO.setImgSong(song.getImgSong());
                    songDTO.setPlays(song.getPlays());
                    songDTO.setGenres(song.getGenres());
                    songDTO.setTimeCreate(song.getTimeCreate());
                    songDTO.setPathSong(song.getPathSong());
                    songDTO.setDescription(song.getDescription());
                    songDTO.setLikeQuantity(likesRepository.getLikeQuantity(song.getId()));
                    songDTO.setSingers(singerRepository.getSinger(song.getId()));
                    return songDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public SongDTO getaSong(int id) {
        Optional<Song>songOptional = songRepository.findById(id);
        Song song = songOptional.get();
        int likeQuantity = likesRepository.getLikeQuantity(id);
        List<String> singers = singerRepository.getSinger(id);
        return new SongDTO(song, likeQuantity, singers);
    }

//    @Override
//    public SongDTO editaSong(SongDTO songDTO) {
//        Optional<Song>songOptional = songRepository.findById(songDTO.getId());
//        Song newSong = songRepository.save(songOptional.get());
//        List<String> singers = singerRepository.getSinger(songDTO.getId());
////        singerRepository.updateSingers(song.getId(), songDTO.getSingers())
//        return new SongDTO(newSong, songDTO.getLikeQuantity(), songDTO.getSingers());
//    }


//    @Override
//    public SongDTO editaSong(SongDTO songDTO) {
//                    SongDTO newsongDTO = new SongDTO();
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
    public SongDTO editaSong(SongDTO songDTO) {

        Song existingSong = songRepository.findById(songDTO.getId())
                .orElseThrow(() -> new RuntimeException("Song not found"));

        existingSong.setId(songDTO.getId());
        existingSong.setNameSong(songDTO.getNameSong());
        existingSong.setImgSong(songDTO.getImgSong());
        existingSong.setPlays(songDTO.getPlays());
        existingSong.setGenres(songDTO.getGenres());
        existingSong.setTimeCreate(songDTO.getTimeCreate());
        existingSong.setPathSong(songDTO.getPathSong());
        existingSong.setDescription(songDTO.getDescription());
        Song savedSong = songRepository.save(existingSong);
        List<String> singers = singerRepository.getSinger(songDTO.getId());
        return new SongDTO(savedSong, likesRepository.getLikeQuantity(songDTO.getId()),singers);
    }
}
