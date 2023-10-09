package com.musicwebbe.service;

import com.musicwebbe.model.Likes;
import org.springframework.data.repository.query.Param;

public interface ILikesService extends IService<Likes> {
    int isLiked(int idSong,int idAccount);
    int getLikeQuantity2(int id);
    void removeLikeBySongIDAndAccountID(int idSong,int idAccount);
}
