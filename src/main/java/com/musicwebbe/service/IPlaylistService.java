package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Playlist;
import com.musicwebbe.model.Song;

import java.util.List;

public interface IPlaylistService extends IService<Playlist>{
    Account getAccount(int id);

}
