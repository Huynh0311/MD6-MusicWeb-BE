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
    private Integer isLiked;

    public SongDTO(int id, String nameSong, String imgSong, String pathSong, int accountID, String playlistImg, String playlistName, int isLiked,String nameSinger) {
        this.id = id;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.accountID = accountID;
        this.pathSong = pathSong;
        this.playlistImg = playlistImg;
        this.playlistName = playlistName;
        this.isLiked = isLiked;
        this.NameSinger = nameSinger;
    }

    public SongDTO(int id, String nameSong, String imgSong, String pathSong, int accountID, int isLiked,String nameSinger) {
        this.id = id;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.accountID = accountID;
        this.pathSong = pathSong;
        this.isLiked = isLiked;
        this.NameSinger = nameSinger;
    }

    public SongDTO(int id, String nameSong, String imgSong, String pathSong, String description, int isLiked) {
        this.id = id;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.pathSong = pathSong;
        this.isLiked = isLiked;
        this.description = description;
    }

    public SongDTO(Integer id, String nameSong, String imgSong, String pathSong, String nameSinger) {
        this.id = id;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.pathSong = pathSong;
        this.NameSinger = nameSinger;
    }

    public SongDTO(int id, String nameSong, String imgSong, String pathSong,String nameSinger, int accountID, String description, Integer isLiked) {
        this.id = id;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.accountID = accountID;
        this.pathSong = pathSong;
        this.description = description;
        this.isLiked = isLiked;
        this.NameSinger = nameSinger;
    }

    public SongDTO(int id, String nameSong, String imgSong, String pathSong, int plays, Genres genres, String nameSinger, String description, int accountID, String accountName,int isLiked) {
        this.id = id;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.pathSong = pathSong;
        this.plays = plays;
        this.genres = genres;
        this.NameSinger = nameSinger;
        this.description = description;
        this.accountID = accountID;
        this.accountName = accountName;
        this.isLiked = isLiked;
    }

}
