package com.musicwebbe.controller;

import com.musicwebbe.model.*;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.service.ISingerService;
import com.musicwebbe.service.ISingerSongService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Autowired
    ISongService iSongService;
    @Autowired
    AccountService accountService;
    @Autowired
    ISingerService iSingerService;
    @Autowired
    ISingerSongService iSingerSongService;

    @PostMapping("/add")
    public ResponseEntity<Song> addSong(@RequestParam("pathImg") String imgSong,
                                        @RequestParam(value = "pathSong") String pathSong,
                                        @RequestParam(value = "nameSong") String nameSong,
                                        @RequestParam(value = "genres_id") int genres_id,
                                        @RequestParam(value = "singer") String[] singer,
                                        @RequestParam(value = "description") String description) {
        String email = "huynh111@gmail.com";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.isAuthenticated()) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            email = userDetails.getUsername();
//        }
        Account account = accountService.findByEmail(email);
        Song song = new Song();
        song.setImgSong(imgSong);
        song.setPathSong(pathSong);
        song.setPlays(0);
        song.setNameSong(nameSong);


        Genres genres = new Genres();
        genres.setId(genres_id);
        song.setGenres(genres);
        song.setDescription(description);
        song.setTimeCreate(LocalDate.now());
        iSongService.save(song);


        for (String sing : singer) {
            Singer singer1 = new Singer();
            String nameSinger = new String();
            nameSinger = sing;
            singer1.setNameSinger(nameSinger);
            singer1.setAccount(account);
            iSingerService.save(singer1);
            SingerSong singerSong = new SingerSong();
            singerSong.setSong(song);
            singerSong.setSinger(singer1);
            iSingerSongService.save(singerSong);
        }
        return new ResponseEntity<>(song,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SongDTO> findSongByID(@PathVariable int id) {
        Song song = iSongService.findSongByIDHQL(id);
        List<Singer> singerList = iSingerService.findListSingerBySongID(id);
        String[]nameList = new String[singerList.size()];
        for(int i = 0;i<singerList.size();i++) {
            nameList[i]=singerList.get(i).getNameSinger();
        }
        SongDTO songDTO = new SongDTO();
        songDTO.setSingerName(nameList);
        BeanUtils.copyProperties(song, songDTO);
        songDTO.setAccountName(singerList.get(0).getAccount().getName());
        songDTO.setAccountID(singerList.get(0).getAccount().getId());
        if (songDTO == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @PostMapping("/play/{id}")
    public int playSong(@PathVariable int id) {
        Song song = iSongService.findById(id);
        int playCount = song.getPlays();
        playCount++;
        song.setPlays(playCount);
        iSongService.edit(song);
        return playCount;
    }

    @GetMapping("/getByGenresID/{id}")
    public ResponseEntity<List<SongDTO>>findAllSongByGenresID(@PathVariable int id){
        Song song = iSongService.findById(id);
        if(song==null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        int songGenresID = song.getGenres().getId();
//        List<Song>songList = iSongService.findAllSongByGenresID(songGenresID);
        List<Song> songList=iSongService.findAllSongByGenresID(songGenresID);
        List<SongDTO>songDTOList = new ArrayList<>();
        for(Song aSong : songList){
            List<Singer> singerList = iSingerService.findListSingerBySongID(aSong.getId());
            SongDTO songDTO = new SongDTO();
            BeanUtils.copyProperties(aSong,songDTO);
            String[]nameList = new String[singerList.size()];
            for(int i = 0;i<singerList.size();i++) {
                nameList[i]=singerList.get(i).getNameSinger();
            }
            songDTO.setSingerName(nameList);
            songDTOList.add(songDTO);
        }

        return new ResponseEntity<>(songDTOList,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Song>> getAll() {
        List<Song> songs = iSongService.getAll();
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("/top5ByPlays")
    public List<Song> getTop5SongsByPlays() {
        List<Song> top5Songs = iSongService.findTop5ByPlaysDesc();
        if (top5Songs.size() > 5) {
            top5Songs = top5Songs.subList(0, 5);
        }
        return top5Songs;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable int id) {
        iSongService.deleteaSong(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongDTO2>> getAllSongs() {
        List<SongDTO2> songDTO2List = iSongService.getAllSong();
        return ResponseEntity.ok(songDTO2List);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO2> getaSong(@PathVariable int id) {
        SongDTO2 songDTO2 = iSongService.getaSong(id);
        return ResponseEntity.ok(songDTO2);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<SongDTO2> editaSong(@PathVariable int id, @RequestBody SongDTO2 songDTO2) {
        if (songDTO2.getId() == id) {
            return new ResponseEntity<>(iSongService.editaSong(songDTO2), HttpStatus.OK);
        } return null;
    }
}
