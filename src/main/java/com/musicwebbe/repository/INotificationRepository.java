package com.musicwebbe.repository;

import com.musicwebbe.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface INotificationRepository extends JpaRepository<Notification,Integer> {
}
