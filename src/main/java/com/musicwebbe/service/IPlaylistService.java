package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.dto.PlaylistDTO;

import java.util.List;

public interface IPlaylistService extends IService<Playlist>{
    Account getAccount(int id);
    List<PlaylistDTO> getAllWithLikeQuantity(Account account);
    PlaylistDTO findByIdWithLikeQuantityAndIsLike(int id,Account account);
    List<Playlist> findByIdAccount(int idAccount);

    void addPlaylist(PlaylistDTO playlistDTO, Integer idAccount) throws Exception;
}
