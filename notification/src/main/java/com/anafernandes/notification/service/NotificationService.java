package com.anafernandes.notification.service;

import com.anafernandes.notification.dto.NotificationDto;
import com.anafernandes.notification.dto.NotificationMapper;
import com.anafernandes.notification.model.Notification;
import com.anafernandes.notification.repository.NotificationRepository;
import com.anafernandes.stock.model.Stock;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }


    @RabbitListener(queues = "stock.queue")
    public void handleMessage(Stock message) {

        if (message.getStockAvailable() > 0) {

            List<Notification> notifications = notificationRepository.getNotificationsByBookId(message.getBookId());

            for (Notification notification : notifications) {

                notification.setCanBeNotified(true);
                notificationRepository.save(notification);
            }
        }


        System.out.println("Received message from queue " + ": " + message.toString());
    }

    public NotificationDto addNotification(NotificationDto notificationRequest) {

        notificationRequest.setDateCreated(LocalDateTime.now());
        notificationRequest.setCanBeNotified(false);

        Notification notification = notificationRepository.saveAndFlush(notificationMapper.toEntity(notificationRequest));

        return notificationMapper.toDto(notification);

    }
}
