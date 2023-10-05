package com.musicwebbe.controller;
import com.musicwebbe.model.*;
import com.musicwebbe.service.ISingerService;
import com.musicwebbe.service.ISingerSongService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

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
    public ResponseEntity<Song>addSong(@RequestParam ("pathImg") String imgSong,
                                        @RequestParam(value="pathSong")String pathSong,
                                        @RequestParam(value="nameSong")String nameSong,
                                        @RequestParam(value="genres_id") int genres_id,
                                        @RequestParam(value="singer") String singer,
                                        @RequestParam(value="description")String description){
        String email = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            email = userDetails.getUsername();
        }
        Account account = accountService.findByEmail(email);
        Singer singer1 = new Singer();
        singer1.setNameSinger(singer);
        singer1.setAccount(account);
        iSingerService.save(singer1);

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

        SingerSong singerSong = new SingerSong();
        singerSong.setSong(song);
        singerSong.setSinger(singer1);
        iSingerSongService.save(singerSong);
        return new ResponseEntity<>(song,HttpStatus.OK);
    }
}
