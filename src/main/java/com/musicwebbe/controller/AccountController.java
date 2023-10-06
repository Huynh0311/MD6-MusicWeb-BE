package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/apiAccount")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/all")
    public ResponseEntity<List<Account>> getAll() {
        List<Account> studentList = accountService.getAll();
        if (studentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Account account) {
        boolean check = accountService.add(account);
        if (check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
