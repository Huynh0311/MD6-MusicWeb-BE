package com.musicwebbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private String nameSinger;
    @ManyToOne
    private Account account;
    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private List<Comment> comments;

}
