package com.musicwebbe.service.impl;

import com.musicwebbe.model.*;
import com.musicwebbe.repository.IPlaylistLikesRepository;
import com.musicwebbe.repository.IPlaylistRepository;
import com.musicwebbe.service.IPlaylistLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlaylistLikesService implements IPlaylistLikesService {
    @Autowired
    IPlaylistLikesRepository iPlaylistLikesRepository;
    @Autowired
    IPlaylistRepository iPlaylistRepository;
    @Override
    public PlaylistLikes save(PlaylistLikes playlistLikes) {
        return iPlaylistLikesRepository.save(playlistLikes);
    }

    @Override
    public PlaylistLikes edit(PlaylistLikes playlistLikes) {
        return iPlaylistLikesRepository.save(playlistLikes);
    }

    @Override
    public void delete(int id) {
        iPlaylistLikesRepository.deleteById(id);
    }

    @Override
    public PlaylistLikes findById(int id) {
        return iPlaylistLikesRepository.findById(id).get();
    }

    @Override
    public List<PlaylistLikes> getAll() {
        return iPlaylistLikesRepository.findAll();
    }

    @Override
    public int isLiked(int idPlaylist, int idAccount) {
        return iPlaylistLikesRepository.isLiked(idPlaylist,idAccount);
    }

    @Override
    public Integer getLikeQuantity(int id) {
        return iPlaylistLikesRepository.countByPlaylistId(id);
    }

    @Override
    public void removeLikeBySongIDAndAccountID(int idSong, int idAccount) {

    }

    @Override
    public Integer likeChecking(Account account, int idPlaylist) {
        if(idPlaylist == 0 || account==null) return null;
        return iPlaylistLikesRepository.isLiked(idPlaylist,account.getId());
    }

    @Override
    public Integer setLiked(Account account, int idPlaylist) {
        int result = iPlaylistLikesRepository.isLiked(idPlaylist, account.getId());
        if (result == 1) {
            result = 0;
            iPlaylistLikesRepository.deleteByPlaylistIdAndAccountId(idPlaylist, account.getId());
        } else {
            try {
                result = 1;
                PlaylistLikes playlistLikes = new PlaylistLikes();
                playlistLikes.setAccount(account);
                Playlist playlist = iPlaylistRepository.findById(idPlaylist).get();
                playlistLikes.setPlaylist(playlist);
                playlistLikes.setPlaylist(playlist);
                iPlaylistLikesRepository.save(playlistLikes);
            }catch (NoSuchElementException e) {
                return -1;
            }
        }
        return result;
    }
}
