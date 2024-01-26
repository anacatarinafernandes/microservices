package com.anafernandes.notification.dto;

import com.anafernandes.notification.model.Notification;
import com.anafernandes.notification.model.Notification.NotificationBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-17T14:10:58+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDto toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setBookId( notification.getBookId() );
        notificationDto.setCustomerEmail( notification.getCustomerEmail() );
        notificationDto.setDateCreated( notification.getDateCreated() );
        notificationDto.setDateNotification( notification.getDateNotification() );
        notificationDto.setCanBeNotified( notification.isCanBeNotified() );

        return notificationDto;
    }

    @Override
    public Notification toEntity(NotificationDto notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationBuilder notification1 = Notification.builder();

        notification1.bookId( notification.getBookId() );
        notification1.customerEmail( notification.getCustomerEmail() );
        notification1.dateCreated( notification.getDateCreated() );
        notification1.dateNotification( notification.getDateNotification() );
        notification1.canBeNotified( notification.isCanBeNotified() );

        return notification1.build();
    }
}
