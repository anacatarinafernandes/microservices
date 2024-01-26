package com.anafernandes.notification.controller;

import com.anafernandes.notification.dto.NotificationDto;
import com.anafernandes.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationDto> addNotification(@RequestBody NotificationDto notificationRequest) {

        NotificationDto notification = notificationService.addNotification(notificationRequest);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
}
