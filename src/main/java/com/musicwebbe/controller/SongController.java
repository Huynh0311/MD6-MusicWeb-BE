package com.musicwebbe.controller;

import com.musicwebbe.model.*;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.service.ISingerService;
import com.musicwebbe.service.ISingerSongService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import com.musicwebbe.service.impl.SongService;
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
import java.util.Arrays;
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

    public Account getCurrentAccount() {
        String email = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            email = userDetails.getUsername();
        }
        Account account = accountService.findByEmail(email);
        return account;
    }

    @PostMapping("/add")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Account account = getCurrentAccount();
        Song savedSong = iSongService.addSong(account, song);
        return new ResponseEntity<>(savedSong, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SongDTO> findSongByID(@PathVariable int id) {
        SongDTO songDTO = iSongService.findSongById(id);
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
    public ResponseEntity<List<SongDTO>> findAllSongByGenresID(@PathVariable int id) {
        Song song = iSongService.findById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        List<SongDTO> listSongDTO = iSongService.getAllSongByGenresID(song);
        return new ResponseEntity<>(listSongDTO, HttpStatus.OK);
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
        }
        return null;
    }
}