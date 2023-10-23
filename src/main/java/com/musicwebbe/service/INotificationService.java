package com.musicwebbe.service;

import com.musicwebbe.model.Notification;

import java.util.List;

public interface INotificationService extends IService<Notification>{
    int countUnreadNotifyByAccountLoginId(int accountId);
    List<Notification> findAllByReceiverId(int accountId);
    void changeStatusNotify(int accountId);
    Notification save(Notification notification);
}
