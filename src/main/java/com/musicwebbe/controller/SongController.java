package com.musicwebbe.controller;

import com.musicwebbe.exeption.EmailExitsException;
import com.musicwebbe.model.*;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.SongDTO;
import com.musicwebbe.model.dto.SongDTO2;
import com.musicwebbe.model.dto.SongFavorite;
import com.musicwebbe.service.ICommentService;
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
    @Autowired
    ICommentService iCommentService;

    public Account getCurrentAccount() {
        try {
            String email = "";
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                email = userDetails.getUsername();
            }
            return accountService.findByEmail(email);

        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSong(@RequestBody Song song) {
        Account account = getCurrentAccount();
        if (account == null) {
            return new ResponseEntity<>("Vui lòng đăng nhập", HttpStatus.UNAUTHORIZED);
        }
        Song savedSong = iSongService.addSong(account, song);
        return new ResponseEntity<>(savedSong, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SongDTO> findSongByID(@PathVariable int id) {
        SongDTO songDTO = iSongService.findSongById(id);
        if (songDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @PostMapping("/play/{id}")
    public ResponseEntity<Integer> playSong(@PathVariable int id) {
        Song song = iSongService.findById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int playCount = song.getPlays();
        playCount++;
        song.setPlays(playCount);
        iSongService.edit(song);
        return new ResponseEntity<>(playCount, HttpStatus.OK);
    }

    @GetMapping("/getByGenresID/{id}")
    public ResponseEntity<List<SongDTO>> findAllSongByGenresID(@PathVariable int id) {
        Song song = iSongService.findById(id);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public List<SongDTO> getTop5SongsByPlays() {
        Account account = getCurrentAccount();
        List<SongDTO> top5Songs = iSongService.findTop5ByPlaysDesc(account);
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

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<SongDTO>> findListSongByName(@PathVariable String name) {
        Account account = getCurrentAccount();
        List<SongDTO> songList = iSongService.findListSongByName(name, account);
        return ResponseEntity.ok(songList);
    }

    @GetMapping("/findBySingerName/{name}")
    public ResponseEntity<List<SongDTO>> findListSongByNameSinger(@PathVariable String name) {
        Account account = getCurrentAccount();
        List<SongDTO> songList = iSongService.findListSongByNameSinger(name, account);
        return ResponseEntity.ok(songList);
    }

    @GetMapping("/findByPlaylist/{name}")
    public ResponseEntity<List<List<SongDTO>>> findListSongByPlaylist(@PathVariable String name) {
        Account account = getCurrentAccount();
        List<List<SongDTO>> songList = iSongService.findListSongByPlaylist(name, account);
        return ResponseEntity.ok(songList);
    }

    @GetMapping("/account")
    public ResponseEntity<List<Song>> getAllSongByAccountId() {
        List<Song> song = iSongService.getAllSongByAccountId(getCurrentAccount().getId());
        return ResponseEntity.ok(song);
    }

    @GetMapping("/checkOwned/{id}")
    public ResponseEntity<Boolean> isSongOwnedByLoggedInAccount(@PathVariable int id) {
        Account account = getCurrentAccount();
        boolean result = iSongService.isSongOwnedByLoggedInAccount(id, account);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/removeComment/{idSong}/{idComment}")
    public ResponseEntity<?> removeCommentInASongByCommentID(@PathVariable int idSong,@PathVariable int idComment) {
        Account account = getCurrentAccount();
        boolean result = iSongService.isSongOwnedByLoggedInAccount(idSong, account);
        if (result == true) {
            Comment comment;
            try {
                comment = iCommentService.findById(idComment);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            iCommentService.delete(comment.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
