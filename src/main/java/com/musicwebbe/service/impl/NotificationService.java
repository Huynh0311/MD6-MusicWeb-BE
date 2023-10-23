package com.musicwebbe.service.impl;

import com.musicwebbe.model.Notification;
import com.musicwebbe.repository.INotificationRepository;
import com.musicwebbe.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepo;
    @Override
    public int countUnreadNotifyByAccountLoginId(int accountId) {
        return notificationRepo.countUnreadNotifyByAccountLoginId(accountId);
    }

    @Override
    public List<Notification> findAllByReceiverId(int accountId) {
        return notificationRepo.findAllByReceiverIdAndOrderByCreateAtDesc(accountId);
    }

    @Override
    public void changeStatusNotify(int accountId) {
        notificationRepo.changeStatusNotify(accountId);
    }

    @Override
    public Notification save(Notification notification) {
        notification.setCreateAt(LocalDateTime.now());
        notification.setStatus(false);
        return notificationRepo.save(notification);
    }

    @Override
    public Notification edit(Notification notification) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Notification findById(int id) {
        return null;
    }

    @Override
    public List<Notification> getAll() {
        return null;
    }
}
