package com.musicwebbe.service.impl;


import com.musicwebbe.exeption.EmailExitsException;
import com.musicwebbe.model.Account;
import com.musicwebbe.model.AccountPrinciple;
import com.musicwebbe.repository.IAccountRepository;
import com.musicwebbe.request.RegisterRequest;
import com.musicwebbe.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService, IAccountService {

    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account findByEmail(String email) {
        return iAccountRepository.findAllByEmail(email);
    }
    public Optional<Account> findAccountByEmail(String email) {
        return iAccountRepository.findAccountByEmail(email);
    }

    public void add(RegisterRequest registerRequest) throws Exception{

        if (iAccountRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailExitsException("Email đã tồn tại");
        }
        Account account = new Account();
        account.setName(registerRequest.getName());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        account.setEmail(registerRequest.getEmail());
        account.setImg(registerRequest.getImg());
        account.setPhone(registerRequest.getPhone());
        account.setRole(registerRequest.getRole());
        iAccountRepository.save(account);
    }

    public boolean save(Account account) {
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

    @Override
    public List<Account> getAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        return iAccountRepository.findById(id).get();
    }
}
