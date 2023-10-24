package com.musicwebbe.model.dto;

import com.musicwebbe.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
    private int id;
    private String namePlaylist;
    int idAccount;
    private String playlistImg;
    private Integer likesQuantity;
    private Integer isLiked;
}
