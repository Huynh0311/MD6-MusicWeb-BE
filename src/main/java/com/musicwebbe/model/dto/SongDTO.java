package com.musicwebbe.model.dto;

import com.musicwebbe.model.Genres;
import com.musicwebbe.model.Singer;
import com.musicwebbe.model.Song;
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
    private String NameSinger;
    private int accountID;
    private String accountName;
    private boolean isAuth;
    private String playlistImg;
    private String playlistName;
    private int isLiked;
    public SongDTO (int id,String nameSong,String imgSong,String pathSong,int accountID,String playlistImg, String playlistName,int isLiked) {
        this.id = id;
        this.nameSong=nameSong;
        this.imgSong=imgSong;
        this.accountID=accountID;
        this.pathSong = pathSong;
        this.playlistImg = playlistImg;
        this.playlistName=playlistName;
        this.isLiked = isLiked;
    }
    public SongDTO (int id,String nameSong,String imgSong,String pathSong,int accountID,int isLiked) {
        this.id = id;
        this.nameSong=nameSong;
        this.imgSong=imgSong;
        this.accountID=accountID;
        this.pathSong = pathSong;
        this.isLiked = isLiked;
    }
}
