package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.PlaylistLikes;

public interface IPlaylistLikesService extends IService<PlaylistLikes> {
    int isLiked(int idSong,int idAccount);
    Integer getLikeQuantity(int id);
    void removeLikeBySongIDAndAccountID(int idSong,int idAccount);

    Integer likeChecking(Account account,int idPlaylist);

    Integer setLiked(Account account, int idSong);
}
