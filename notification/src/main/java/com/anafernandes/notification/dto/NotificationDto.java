package com.anafernandes.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Integer bookId;
    private String customerEmail;
    private LocalDateTime dateCreated;
    private LocalDateTime dateNotification;
    private boolean canBeNotified;
}
