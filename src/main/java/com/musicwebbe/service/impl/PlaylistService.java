package com.musicwebbe.service.impl;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Playlist;
import com.musicwebbe.repository.IAccountRepository;
import com.musicwebbe.repository.IPlaylistRepository;
import com.musicwebbe.repository.IPlaylistSongRepository;
import com.musicwebbe.service.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements IPlaylistService {
    @Autowired
    private IPlaylistRepository iPlaylistRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private IPlaylistSongRepository iPlaylistSongRepository;
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
    public List<Playlist> getAll() {
        return iPlaylistRepository.findAll();
    }

    @Override
    public Account getAccount(int id) {
        return iAccountRepository.findById(iPlaylistRepository.getAccount(id)).get();
    }
}
