package com.musicwebbe.controller;

import com.musicwebbe.model.dto.SongDTO;
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
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<SongDTO> songDTOList = songService.getAllSong();
        return ResponseEntity.ok(songDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getaSong(@PathVariable int id) {
        SongDTO songDTO = songService.getaSong(id);
        return ResponseEntity.ok(songDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SongDTO> editaSong(@PathVariable int id, @RequestBody SongDTO songDTO) {
        if (songDTO.getId()==id) {
            return new ResponseEntity<>(songService.editaSong(songDTO), HttpStatus.OK);
        } else return null;

    }

//        @PostMapping("/{id}")
//    public ResponseEntity<SongDTO> editaSong(@PathVariable int id, @RequestBody Song song, List<String> singer) {
//        if (song.getId()==id) {
//            return new ResponseEntity<>(songService.editaSong(song, singer), HttpStatus.OK);
//        } else return null;
//
//    }


}
