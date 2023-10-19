package com.musicwebbe.repository;

import com.musicwebbe.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
    Account findAllByEmail(String username);

    boolean existsByEmail(String email);

    Optional<Account> findAccountByEmail(String email);

    List<Account> getAllByIsAuthOrderByIdDesc(boolean isAuth);
}
