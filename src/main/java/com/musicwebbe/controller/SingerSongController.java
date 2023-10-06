package com.musicwebbe.controller;

import com.musicwebbe.model.SingerSong;
import com.musicwebbe.model.Song;
import com.musicwebbe.service.ISingerSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/SingerSong")
public class SingerSongController {

    @Autowired
    ISingerSongService iSingerService;

    @PostMapping("/creatSingerSong")
    public ResponseEntity<SingerSong> save(@RequestBody SingerSong singerSong) {
        SingerSong savedSingerSong = iSingerService.save(singerSong);
        return new ResponseEntity<>(savedSingerSong, HttpStatus.OK);
    }

    @GetMapping("/SingerSong")
    public ResponseEntity<List<SingerSong>> getAll() {
        List<SingerSong> singerSongs = iSingerService.getAll();
        if (singerSongs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(singerSongs, HttpStatus.OK);
    }

    @GetMapping("/{idSingerSong}")
    @ResponseBody
    public ResponseEntity<SingerSong> getAccount(@PathVariable int idSingerSong) {
        SingerSong singerSong = iSingerService.findById(idSingerSong).get();
        return new ResponseEntity<>(singerSong, HttpStatus.OK);
    }

    @GetMapping("/getSongsBySinger/{singerId}")
    public ResponseEntity<List<Song>> getSongsBySinger(@PathVariable int singerId) {
        List<Song> songs = iSingerService.getAllSongsBySingerId(singerId);
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}

