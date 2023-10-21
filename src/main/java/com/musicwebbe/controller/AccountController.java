package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.AccountDTO2;
import com.musicwebbe.model.dto.SongFavorite;
import com.musicwebbe.service.IAccountService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/apiAccount")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISongService iSongService;

    @GetMapping("/all")
    public List<Account> getAll() {
        return iAccountService.getAll();
    }

    @PostMapping("/saveAccount/{id}")
    public ResponseEntity<Account> save(@PathVariable int id, @RequestBody Account account) {
        Account accFindId = accountService.findById(id);
        accFindId.setName(account.getName());
        accFindId.setEmail(account.getEmail());
        accFindId.setPhone(account.getPhone());
        accFindId.setImg(account.getImg());
        Account accountSave = accountService.save(accFindId);
        try {
            return new ResponseEntity<>(accountSave, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Account> savePassword(@RequestBody Account account) {
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);
        Account accountSave = accountService.save(account);
        try {
            return new ResponseEntity<>(accountSave, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findById/{id}")
    public Account findById(@PathVariable int id) {
        return iAccountService.findById(id);
    }

    @GetMapping("/findByAuth")
    public List<AccountDTO2> findAccountByAut() {
        List<AccountDTO2> accountList = iAccountService.getAllByIsAuth();
        return accountList;
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<SongFavorite>> songFavorite() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<SongFavorite> songs = iSongService.getAllFavoritesByUser(userDetails.getUsername());
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
