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
    @Lob
    private String imgSong;
    private int plays;
    @ManyToOne
    private Genres genres;
    private LocalDate timeCreate;
    @Lob
    private String description;
    @Lob
    private String pathSong;
}
