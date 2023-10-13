package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.Playlist;

public interface IPlaylistService extends IService<Playlist>{
    Account getAccount(int id);
}
