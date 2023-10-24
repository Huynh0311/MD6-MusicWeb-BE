package com.musicwebbe.controller;

import com.musicwebbe.model.Notification;
import com.musicwebbe.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/notification")
    public void sendMessage(@RequestBody Notification notification){
        simpMessagingTemplate.convertAndSend("/notification/" + notification.getReceiver().getId(), notification);
    }

    @GetMapping("/count-unread/{accountId}")
    public int countUnreadNotifyByAccountLoginId(@PathVariable int accountId) {
        try {
            return notificationService.countUnreadNotifyByAccountLoginId(accountId);
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> findAllByReceiverId(@PathVariable int accountId) {
        try {
            return ResponseEntity.ok(notificationService.findAllByReceiverId(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @PutMapping("/change-status/{accountId}")
    public ResponseEntity<?> changeStatusNotify(@PathVariable int accountId) {
        try {
            notificationService.changeStatusNotify(accountId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveNotify(@RequestBody Notification notification) {
        try {
            return ResponseEntity.ok(notificationService.save(notification));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }
}
