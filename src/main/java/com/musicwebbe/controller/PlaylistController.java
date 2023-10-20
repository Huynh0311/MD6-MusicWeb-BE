package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.PlaylistSong;
import com.musicwebbe.model.Song;
import com.musicwebbe.service.impl.PlaylistService;
import com.musicwebbe.service.impl.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private PlaylistSongService playlistSongService;

    @GetMapping("/all")
    public ResponseEntity<List<Playlist>> getAll() {
        return new ResponseEntity<>(playlistService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Playlist> findById(@PathVariable int id) {
        return new ResponseEntity<>(playlistService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/countSong/{id}")
    public ResponseEntity<Integer> countSong(@PathVariable int id) {
        return new ResponseEntity<>(playlistSongService.countSong(id), HttpStatus.OK);
    }

    @DeleteMapping("/deletePlaylist/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable int id) {
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSongByPlaylist/{id}")
    public ResponseEntity<List<Song>> getSongByPlaylist(@PathVariable int id) {
        return new ResponseEntity<>(playlistSongService.findAllByPlaylist(id), HttpStatus.OK);
    }

    @GetMapping("/getUserByPlaylist/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        return new ResponseEntity<>(playlistService.getAccount(id), HttpStatus.OK);
    }

    @GetMapping("/findByAccountId/{id}")
    public ResponseEntity<List<Playlist>> findByAccountId(@PathVariable int id) {
        return new ResponseEntity<>(playlistService.findByIdAccount(id), HttpStatus.OK);
    }

    @PostMapping("/saveToPlaylist")
    public ResponseEntity<?> findByAccountId(@RequestBody PlaylistSong playlistSong) {
        try {
            playlistSongService.save(playlistSong);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
