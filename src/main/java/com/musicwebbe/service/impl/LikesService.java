package com.musicwebbe.service.impl;

import com.musicwebbe.model.Likes;
import com.musicwebbe.repository.ILikesRepository;
import com.musicwebbe.service.ILikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesService implements ILikesService {
    @Autowired
    ILikesRepository iLikesRepository;
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
    public int isLiked(int idSong,int idAccount) {
        return iLikesRepository.isLiked(idSong,idAccount);
    }

    @Override
    public int getLikeQuantity(int id) {
        return iLikesRepository.getLikeQuantity(id);
    }

    @Override
    public void removeLikeBySongIDAndAccountID(int idSong, int idAccount) {
        iLikesRepository.removeLikeBySongIDAndAccountID(idSong,idAccount);
    }


}
