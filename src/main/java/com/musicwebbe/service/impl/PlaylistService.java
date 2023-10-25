package com.musicwebbe.service.impl;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.dto.PlaylistDTO;
import com.musicwebbe.repository.IAccountRepository;
import com.musicwebbe.repository.IPlaylistLikesRepository;
import com.musicwebbe.repository.IPlaylistRepository;
import com.musicwebbe.repository.IPlaylistSongRepository;
import com.musicwebbe.service.IPlaylistService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService implements IPlaylistService {
    @Autowired
    private IPlaylistRepository iPlaylistRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private IPlaylistSongRepository iPlaylistSongRepository;
    @Autowired
    private IPlaylistLikesRepository iPlaylistLikesRepository;

    @Override
    public Playlist save(Playlist playlist) {
        return null;
    }

    @Override
    public Playlist edit(Playlist playlist) {
        return null;
    }

    @Override
    public void delete(int id) {
        iPlaylistSongRepository.deleteByPlaylistId(id);
        iPlaylistRepository.deleteById(id);
    }

    @Override
    public Playlist findById(int id) {
        return iPlaylistRepository.findById(id).get();
    }

    @Override
    public PlaylistDTO findByIdWithLikeQuantityAndIsLike(int id, Account account) {
        Playlist playlist = iPlaylistRepository.findById(id).get();
        if (playlist == null) {
            return null;
        }
        Integer playlistLikesQuantity = iPlaylistLikesRepository.countByPlaylistId(playlist.getId());
        Integer isLiked = 0;
        if (account != null) {
            isLiked = iPlaylistLikesRepository.isLiked(playlist.getId(), account.getId());
        }
        PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getNamePlaylist(), playlist.getAccount().getId(), playlist.getPlaylistImg(), playlistLikesQuantity, isLiked);
        return playlistDTO;
    }

    @Override
    public List<Playlist> getAll() {
        return iPlaylistRepository.findAll();
    }

    @Override
    public Account getAccount(int id) {
        return iAccountRepository.findById(iPlaylistRepository.getAccount(id)).get();
    }

    @Override
    public List<PlaylistDTO> getAllWithLikeQuantity(Account account) {
        List<Playlist> playlistList = iPlaylistRepository.findAll();
        List<PlaylistDTO> playlistDTOList = playlistList.stream()
                .map(playlist -> {
                    Integer playlistLikesQuantity = iPlaylistLikesRepository.countByPlaylistId(playlist.getId());
                    Integer isLiked = 0;
                    if (account != null) {
                        isLiked = iPlaylistLikesRepository.isLiked(playlist.getId(), account.getId());
                    }
                    return new PlaylistDTO(playlist.getId(), playlist.getNamePlaylist(), playlist.getAccount().getId(), playlist.getPlaylistImg(), playlistLikesQuantity, isLiked);
                }).collect(Collectors.toList());
        return playlistDTOList;
    }
}
