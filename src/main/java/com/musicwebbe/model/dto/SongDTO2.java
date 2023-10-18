package com.musicwebbe.model.dto;

import com.musicwebbe.model.Genres;
import com.musicwebbe.model.Singer;
import com.musicwebbe.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO2 {

    private int id;
    private String nameSong;
    private String imgSong;
    private int plays;
    private Genres genres;
    private LocalDate timeCreate;
    private String description;
    private String pathSong;
    private int likeQuantity;
    private String nameSinger;
    private List<CommentDTO> comments;

    public SongDTO2(Song song, int likeQuantity) {
        this.id = song.getId();
        this.nameSong = song.getNameSong();
        this.imgSong = song.getImgSong();
        this.plays = song.getPlays();
        this.genres = song.getGenres();
        this.timeCreate = song.getTimeCreate();
        this.description = song.getDescription();
        this.pathSong = song.getPathSong();
        this.likeQuantity = likeQuantity;
        this.nameSinger = song.getNameSinger();
    }

}
