package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Song;
import com.musicwebbe.model.dto.SongFavorite;
import com.musicwebbe.service.ILikesService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    ILikesService iLikesService;
    @Autowired
    ISongService iSongService;
    @Autowired
    AccountService accountService;

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

    @PostMapping("/check")
    public ResponseEntity<Integer> likeCheck(@RequestBody Likes likes) {
        int idSong = likes.getSong().getId();
        int idAccount = likes.getAccount().getId();
        int checkResult = iLikesService.likeChecking(idAccount, idSong);
        return new ResponseEntity<>(checkResult, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/setlike/{id}")
    public ResponseEntity<Integer> setLike(@PathVariable int id) {
        Account account = getCurrentAccount();
        int idSong = id;
        if (idSong == 0 || account.getId() == 0) {
            return null;
        }
        int result = iLikesService.setLiked(account, idSong);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/getlike/{id}")
    public ResponseEntity<Integer> getLikeQuantity(@PathVariable int id) {
        Integer songLikedQuantity = iLikesService.getLikeQuantity2(id);
        return ResponseEntity.ok(songLikedQuantity != null ? songLikedQuantity : 0);
    }

}
