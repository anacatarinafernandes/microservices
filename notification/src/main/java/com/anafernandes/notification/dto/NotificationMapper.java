package com.anafernandes.notification.dto;

import com.anafernandes.notification.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDto(Notification notification);

    Notification toEntity(NotificationDto notification);

}
