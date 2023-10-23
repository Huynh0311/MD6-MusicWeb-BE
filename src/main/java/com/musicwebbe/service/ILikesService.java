package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Song;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ILikesService extends IService<Likes> {
    int isLiked(int idSong,int idAccount);
    int getLikeQuantity2(int id);
    void removeLikeBySongIDAndAccountID(int idSong,int idAccount);

    Integer likeChecking(int idAccount,int idSong);

    public Integer setLiked(Account account, int idSong);
}
