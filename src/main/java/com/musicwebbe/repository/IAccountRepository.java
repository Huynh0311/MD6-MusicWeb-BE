package com.musicwebbe.repository;

import com.musicwebbe.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    Account findAllByEmail(String username);

}
