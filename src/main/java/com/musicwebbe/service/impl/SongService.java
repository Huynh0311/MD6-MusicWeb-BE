package com.musicwebbe.service.impl;

import com.musicwebbe.model.*;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.repository.*;

import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ISingerService;
import com.musicwebbe.service.ISingerSongService;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService implements ISongService {
    @Autowired
    ISingerSongService iSingerSongService;
    @Autowired
    ISingerService iSingerService;

    @Autowired
    ISongRepository iSongRepository;

    @Autowired
    ILikesRepository likesRepository;

    @Autowired
    ISingerRepository isingerRepository;

    @Autowired
    ISingerSongRepository iSingerSongRepository;
    @Autowired
    IAccountRepository iAccountRepository;


    @Override
    public Song save(Song song) {
        return iSongRepository.save(song);
    }


    @Override
    public Song edit(Song song) {
        return iSongRepository.save(song);
    }

    @Override
    public void delete(int id) {
        iSongRepository.deleteById(id);
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
    public Song findSongByIDHQLAuth(int id) {
        return iSongRepository.findSongByIDHQLAuth(id);
    }

    @Override
    public Song findSongByIDHQLNotAuth(int id) {
        return iSongRepository.findSongByIDHQLNotAuth(id);
    }


    @Override
    public List<Song> findAllSongByGenresIDAuth(int id) {
        return iSongRepository.findAllSongByGenresIDAuth(id);
    }

    @Override
    public List<Song> findAllSongByGenresIDNotAuth(int id) {
        return iSongRepository.findAllSongByGenresIDNotAuth(id);
    }

    @Override
    public List<Song> findTop5ByPlaysDesc() {
        return iSongRepository.findTop5ByPlaysDesc();
    }

    @Override
    public void deleteaSong(int id) {
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
    @Override
    public Song addSong(Account account,Song song) {
        song.setPlays(0);
        song.setTimeCreate(LocalDate.now());
        song.setAccount(account);
        iSongRepository.save(song);
        if (account.isAuth() == true) {
            Singer singer = iSingerService.findSingerByAccountID(account.getId());
            SingerSong singerSong = new SingerSong();
            singerSong.setSinger(singer);
            singerSong.setSong(song);
            iSingerSongService.save(singerSong);
        }
        return song;
    }

    public SongDTO findSongById(Account account,int id){
        Song song = account.isAuth() ? iSongRepository.findSongByIDHQLAuth(id) : iSongRepository.findSongByIDHQLNotAuth(id);
        SongDTO songDTO = new SongDTO();
        BeanUtils.copyProperties(song, songDTO);
        account = iAccountRepository.findById(song.getAccount().getId()).get();
        songDTO.setAccountName(account.getName());
        songDTO.setAccountID(account.getId());
        return songDTO;
    }

    @Override
    public List<SongDTO> getAllSongByGenresID(Account account, Song song) {
        int songGenresID = song.getGenres().getId();
        List<Song> songList;
        if (account.isAuth()) {
            songList = iSongRepository.findAllSongByGenresIDAuth(songGenresID);
        } else {
            songList = iSongRepository.findAllSongByGenresIDNotAuth(songGenresID);
        }
        List<SongDTO> songDTOList = new ArrayList<>();
        for (Song aSong : songList) {
            SongDTO songDTO = new SongDTO();
            BeanUtils.copyProperties(aSong, songDTO);
            songDTOList.add(songDTO);
        }
        return songDTOList;
    }

}
