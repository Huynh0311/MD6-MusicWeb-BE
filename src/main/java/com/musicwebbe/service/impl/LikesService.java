package com.musicwebbe.service.impl;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Likes;
import com.musicwebbe.model.Song;
import com.musicwebbe.repository.ILikesRepository;
import com.musicwebbe.repository.ISongRepository;
import com.musicwebbe.service.ILikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LikesService implements ILikesService {
    @Autowired
    ILikesRepository iLikesRepository;
    @Autowired
    ISongRepository iSongRepository;

    @Override
    public Likes save(Likes likes) {
        return iLikesRepository.save(likes);
    }

    @Override
    public Likes edit(Likes likes) {
        return null;
    }

    @Override
    public void delete(int id) {
        iLikesRepository.deleteById(id);
    }

    @Override
    public Likes findById(int id) {
        return iLikesRepository.findById(id).get();
    }

    @Override
    public List<Likes> getAll() {
        return iLikesRepository.findAll();
    }

    @Override
    public int isLiked(int idSong, int idAccount) {
        return iLikesRepository.isLiked(idSong, idAccount);
    }

    @Override
    public int getLikeQuantity2(int id) {
        return iLikesRepository.getLikeQuantity2(id);
    }

    @Override
    public void removeLikeBySongIDAndAccountID(int idSong, int idAccount) {
        iLikesRepository.removeLikeBySongIDAndAccountID(idSong, idAccount);
    }

    @Override
    public Integer likeChecking(int idAccount, int idSong) {
        if (idSong == 0 || idAccount == 0) {
            return null;
        }
        int result = iLikesRepository.isLiked(idSong, idAccount);
        return result;
    }

    @Override
    public Integer setLiked(Account account, int idSong) {
        int result = iLikesRepository.isLiked(idSong, account.getId());
        if (result == 1) {
            result = 0;
            iLikesRepository.removeLikeBySongIDAndAccountID(idSong, account.getId());
        } else {
            result = 1;
            Likes like = new Likes();
            like.setAccount(account);
            Song song = iSongRepository.findSongByIDHQL(idSong);
            like.setSong(song);
            iLikesRepository.save(like);
        }
        return result;
    }

}
