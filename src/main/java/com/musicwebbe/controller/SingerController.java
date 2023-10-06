package com.musicwebbe.controller;

import com.musicwebbe.model.Singer;
import com.musicwebbe.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/singer")
public class SingerController {

    @Autowired
    ISingerService iSingerService;

    @PostMapping("/creatSinger")
    public ResponseEntity<Singer> save(@RequestBody Singer singer) {
        Singer savedSinger = iSingerService.save(singer);
        return new ResponseEntity<>(savedSinger, HttpStatus.OK);
    }

    @GetMapping("/singers")
    public ResponseEntity<List<Singer>> getAll() {
        List<Singer> singers = iSingerService.getAll();
        if (singers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(singers, HttpStatus.OK);
    }

    @GetMapping("/{idSinger}")
    @ResponseBody
    public ResponseEntity<Singer> getAccount(@PathVariable int idSinger) {
        Singer singer = iSingerService.findById(idSinger).get();
        return new ResponseEntity<>(singer, HttpStatus.OK);
    }

}

