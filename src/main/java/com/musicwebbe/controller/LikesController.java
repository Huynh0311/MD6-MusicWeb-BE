package com.musicwebbe.controller;

import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Song;
import com.musicwebbe.service.ILikesService;
import com.musicwebbe.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin("*")
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    ILikesService iLikesService;
    @Autowired
    ISongService iSongService;

    @PostMapping("/check")
    public ResponseEntity<Integer> likeCheckAndSet(@RequestBody Likes likes) {
        int idSong = likes.getSong().getId();
        int idAccount = likes.getAccount().getId();
        if (idSong == 0 || idAccount == 0) {
            return null;
        }
        int result = iLikesService.isLiked(idSong, idAccount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/setlike")
    public ResponseEntity<Integer> setLike(@RequestBody Likes likes) {
        int idSong = likes.getSong().getId();
        int idAccount = likes.getAccount().getId();
        if (idSong == 0 || idAccount == 0) {
            return null;
        }
        int result = iLikesService.isLiked(idSong, idAccount);
        if (result == 1) {
            result = 0;
            iLikesService.removeLikeBySongIDAndAccountID(idSong, idAccount);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result = 1;
            Likes like = new Likes();
            like.setAccount(likes.getAccount());
            like.setSong(likes.getSong());
            iLikesService.save(like);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    @GetMapping("/getlike/{id}")
    public ResponseEntity<Integer> getLikeQuantity(@PathVariable int id) {
        Integer songLikedQuantity = iLikesService.getLikeQuantity2(id);
        return ResponseEntity.ok(songLikedQuantity != null ? songLikedQuantity : 0);
    }
}
