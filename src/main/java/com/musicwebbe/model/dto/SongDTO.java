package com.musicwebbe.model.dto;

import com.musicwebbe.model.Genres;
import com.musicwebbe.model.Singer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private int id;
    private String nameSong;
    private String imgSong;
    private int plays;
    private Genres genres;
    private LocalDate timeCreate;
    private String description;
    private String pathSong;
    private String[] singerName;
    private int accountID;
    private String accountName;
}
