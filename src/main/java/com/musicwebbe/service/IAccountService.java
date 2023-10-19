package com.musicwebbe.service;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.AccountDTO2;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    Account findById(int id);

    List<AccountDTO2> getAllByIsAuth();

    Integer getAccountQuantity();
}
