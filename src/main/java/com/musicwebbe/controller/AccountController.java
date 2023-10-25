package com.musicwebbe.controller;

import com.musicwebbe.model.Account;
import com.musicwebbe.model.dto.AccountDTO;
import com.musicwebbe.model.dto.AccountDTO2;
import com.musicwebbe.model.dto.ChangePasswordDTO;
import com.musicwebbe.model.dto.SongFavorite;
import com.musicwebbe.service.IAccountService;
import com.musicwebbe.service.ISongService;
import com.musicwebbe.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    private JavaMailSender mailSender;

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
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Account> savePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        Account accountLogin = iAccountService.findById(changePasswordDTO.getId());
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), accountLogin.getPassword())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else {
            try {
                String newPassword = passwordEncoder.encode(changePasswordDTO.getPassword());
                changePasswordDTO.setPassword(newPassword);
                Account accountSave = accountService.save(changePasswordDTO);
                return new ResponseEntity<>(accountSave, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/findById/{id}")
    public Account findById(@PathVariable int id) {
        return iAccountService.findById(id);
    }

    @GetMapping("/findByAuth")
    public List<AccountDTO2> findAccountByAut() {
        return iAccountService.getAllByIsAuth();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<SongFavorite>> songFavorite() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<SongFavorite> songs = iSongService.getAllFavoritesByUser(userDetails.getUsername());
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping("/informationEmail/{id}")
    public ResponseEntity<?> sendInformationEmail(@PathVariable int id) throws MessagingException, UnsupportedEncodingException {
        try {
            Account account = accountService.findById(id);
            if (account == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                String subject = "Cảm ơn bạn đã gửi thông tin xác thực tới chúng tôi";
                String senderName = "MusicG1 Admin";
                String mailContent = "<p>Thân gửi " + account.getName() + ",</p>";
                mailContent += "<p>Cảm ơn bạn đã gửi thông tin xác thực với <strong>tên đăng ký:</strong> " + account.getName() + " và <strong>số Điện thoại:</strong> " + account.getPhone();
                mailContent += "<p>Chúng tôi sẽ tiến hành kiểm tra và thực hiện xác thực trong thời gian sớm nhất";
                mailContent += "<p>Thân,</p>";
                mailContent += "<p>Admin Đạt</p>";
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setFrom("musicwebc04@gmail.com", senderName);
                helper.setTo(account.getEmail());
                helper.setSubject(subject);
                helper.setText(mailContent, true);
                mailSender.send(message);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
