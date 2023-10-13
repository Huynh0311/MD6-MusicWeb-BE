package com.musicwebbe.model.dto;

import com.musicwebbe.model.Genres;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongFavorite {
    private Integer id;
    private String nameSong;
    private String imgSong;
    private Integer plays;
    private String genreName;
    private LocalDate timeCreate;
    private String description;
    private String pathSong;
    private Integer likeQuantity;
    private String nameSinger;
}
