package com.musicwebbe.controller;

import com.musicwebbe.model.Genres;
import com.musicwebbe.model.Song;
import com.musicwebbe.service.IGenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/genres")
public class GenresController {
    @Autowired
    IGenresService iGenresService;
    @GetMapping
    public ResponseEntity<List<Genres>> getAll() {
        List<Genres> genresList = iGenresService.getAll();
        if (genresList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(genresList, HttpStatus.OK);
    }
}
