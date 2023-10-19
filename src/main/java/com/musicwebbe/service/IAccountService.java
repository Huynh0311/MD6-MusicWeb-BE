package com.musicwebbe.service;

import com.musicwebbe.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();
    Account findById(int id);

    Integer getAccountQuantity();
}
