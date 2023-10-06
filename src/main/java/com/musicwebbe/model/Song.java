package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameSong;
    private String imgSong;
    private int plays;
    private String Genres;
    private LocalDate timeCreate;
    @Lob
    private String description;
    @Lob
    private String pathSong;
}
