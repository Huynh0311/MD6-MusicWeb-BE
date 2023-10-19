package com.musicwebbe.controller;

import com.musicwebbe.model.Singer;
import com.musicwebbe.service.IAccountService;
import com.musicwebbe.service.ISingerService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAccountService iAccountService;
    @Autowired
    ISongService iSongService;
    @Autowired
    ISingerService iSingerService;

    @GetMapping("/userquantity")
    public Integer getAllUser() {
        return iAccountService.getAccountQuantity();
    }

    @GetMapping("/songquantity")
    public long getAllSong() {
        return iSongService.getTotalSongs();
    }

    @GetMapping("singerquantity")
    public List<Singer> getAllSinger() { return iSingerService.getSingerQuantity(); }
}
