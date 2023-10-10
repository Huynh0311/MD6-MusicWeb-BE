package com.musicwebbe.service.impl;


import com.musicwebbe.model.Account;
import com.musicwebbe.model.AccountPrinciple;
import com.musicwebbe.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private IAccountRepository iAccountRepository;

    public Account findByEmail(String email) {
        return iAccountRepository.findAllByEmail(email);
    }
    public Optional<Account> findAccountByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email);
    }

    public boolean add(Account account) {
        if(iAccountRepository.findAccountByEmail(account.getEmail()).isPresent()) {
            return false;
        }
        iAccountRepository.save(account);
        return true;
    }

    public UserDetails loadUserByUsername(String email) {
        List<Account> accounts = iAccountRepository.findAll();
        for (Account account : accounts) {
            if (Objects.equals(account.getEmail(), email)) {
                return AccountPrinciple.build(account);
            }
        }
        return null;
    }
    public List<Account> getAll(){
        return iAccountRepository.findAll();
    }
}
