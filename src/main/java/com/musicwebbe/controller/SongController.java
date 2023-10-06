package com.musicwebbe.controller;

import com.musicwebbe.model.Song;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    ISongService iSongService;

    @PostMapping("/creatSingerSong")
    public ResponseEntity<Song> save(@RequestBody Song song) {
        Song savedSong = iSongService.save(song);
        return new ResponseEntity<>(savedSong, HttpStatus.OK);
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


}

