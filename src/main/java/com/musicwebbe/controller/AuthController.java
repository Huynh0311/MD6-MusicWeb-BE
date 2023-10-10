package com.musicwebbe.controller;


import com.musicwebbe.jwt.service.JwtResponse;
import com.musicwebbe.jwt.service.JwtService;
import com.musicwebbe.model.Account;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account userInfo = accountService.findByEmail(account.getEmail());
        return ResponseEntity.ok(new JwtResponse(userInfo.getId(), jwt,
                userInfo.getEmail(), userInfo.getName(),userInfo.getImg(), userDetails.getAuthorities()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody Account account) {
        if (!account.getPassword().isEmpty()){
            String password = passwordEncoder.encode(account.getPassword());
            account.setPassword(password);
        }
        boolean check = accountService.add(account);
        if (check) {
            return new ResponseEntity<>(account,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<?> check(@RequestBody Account account) {
        boolean check = true;
        if (accountService.findAccountByEmail(account.getEmail()).isPresent()){
            check = false;
        }
        if (!check) {
            return new ResponseEntity<>(account,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(account,HttpStatus.BAD_REQUEST);
        }
    }
}
