package com.musicwebbe.controller;

import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.service.ILikesService;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
@CrossOrigin("*")
public class SongController {

    @Autowired
    ISongService songService;

    @Autowired
    ILikesService likesService;

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable int id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongDTO2>> getAllSongs() {
        List<SongDTO2> songDTO2List = songService.getAllSong();
        return ResponseEntity.ok(songDTO2List);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO2> getaSong(@PathVariable int id) {
        SongDTO2 songDTO2 = songService.getaSong(id);
        return ResponseEntity.ok(songDTO2);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SongDTO2> editaSong(@PathVariable int id, @RequestBody SongDTO2 songDTO2) {
        if (songDTO2.getId()==id) {
            return new ResponseEntity<>(songService.editaSong(songDTO2), HttpStatus.OK);
        } else return null;

    }

//        @PostMapping("/{id}")
//    public ResponseEntity<SongDTO2> editaSong(@PathVariable int id, @RequestBody Song song, List<String> singer) {
//        if (song.getId()==id) {
//            return new ResponseEntity<>(songService.editaSong(song, singer), HttpStatus.OK);
//        } else return null;
//
//    }


}
