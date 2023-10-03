package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String namePlaylist;
    @ManyToOne
    private Account account;
}
