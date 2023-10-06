package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.service.IAccountService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/apiAccount")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    IAccountService iAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public List<Account> getAll() {
        return iAccountService.getAll();
    }

    @PostMapping("/saveAccount/{id}")
    public ResponseEntity<?> save(@PathVariable int id, @RequestBody Account account) {
        Account accFindId = accountService.findById(id);
        accFindId.setName(account.getName());
        accFindId.setEmail(account.getEmail());
        accFindId.setPhone(account.getPhone());
        accFindId.setImg(account.getImg());
        boolean check = accountService.save(accFindId);
        if (check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePassword(@RequestBody Account account) {
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);
        boolean check = accountService.save(account);
        if (check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findById/{id}")
    public Account findById(@PathVariable int id) {
        return iAccountService.findById(id);
    }
}
