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
@RequestMapping("/api")
public class SingerSongController {

    @Autowired
    ISingerSongService iSingerSongService;

    @PostMapping("/creatSingerSong")
    public ResponseEntity<SingerSong> save(@RequestBody SingerSong singerSong) {
        SingerSong savedSingerSong = iSingerSongService.save(singerSong);
        return new ResponseEntity<>(savedSingerSong, HttpStatus.OK);
    }

    @GetMapping("/SingerSong")
    public ResponseEntity<List<SingerSong>> getAll() {
        List<SingerSong> singerSongs = iSingerSongService.getAll();
        if (singerSongs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(singerSongs, HttpStatus.OK);
    }

    @GetMapping("/{idSingerSong}")
    @ResponseBody
    public ResponseEntity<SingerSong> getSingerSongById(@PathVariable int idSingerSong) {
        SingerSong singerSong = iSingerSongService.findById(idSingerSong).get();
        return new ResponseEntity<>(singerSong, HttpStatus.OK);
    }

    @GetMapping("/getSongsBySinger/{singerName}")
    public ResponseEntity<List<Song>> getSongsBySinger(@PathVariable String singerName) {
        List<Song> songs = iSingerSongService.findAllSongsBySimilarSingerName(singerName);
        if (songs == null || songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}

