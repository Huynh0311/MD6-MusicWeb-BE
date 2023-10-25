package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Likes;
import com.musicwebbe.service.IAccountService;
import com.musicwebbe.service.IPlaylistLikesService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlistLikes")
public class PlaylistLikesController {
    @Autowired
    IPlaylistLikesService iPlaylistLikesService;
    @Autowired
    AccountService accountService;

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

    @PostMapping("/check/{id}")
    public ResponseEntity<Integer> likeCheck(@PathVariable int id) {
        Account account = getCurrentAccount();
        Integer checkResult = iPlaylistLikesService.likeChecking(account, id);
        return new ResponseEntity<>(checkResult, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/setlike/{id}")
    public ResponseEntity<Integer> setLike(@PathVariable int id) {
        Account account = getCurrentAccount();
        if (id == 0 || account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Integer result = iPlaylistLikesService.setLiked(account, id);
        if(result==-1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getLike/{id}")
    public ResponseEntity<Integer> getLikeQuantity(@PathVariable int id) {
        Integer playlistLikedQuantity = iPlaylistLikesService.getLikeQuantity(id);
        return ResponseEntity.ok(playlistLikedQuantity != null ? playlistLikedQuantity : 0);
    }
}
