package com.anafernandes.notification.repository;

import com.anafernandes.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query("SELECT n FROM Notification n WHERE n.bookId = ?1")
    List<Notification> getNotificationsByBookId(Integer bookId);

    @Query("SELECT n FROM Notification n WHERE n.canBeNotified = ?1")
    List<Notification> getNotificationsByCanBeNotified(boolean canBeNotified);
}
