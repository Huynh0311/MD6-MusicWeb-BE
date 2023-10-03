package com.musicwebbe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Playlist playlist;
    @ManyToOne
    private Song song;
}
