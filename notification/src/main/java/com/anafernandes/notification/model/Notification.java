package com.anafernandes.notification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"

    )
    private Integer id;
    @Column(nullable = false)
    private Integer bookId;
    @Column(nullable = false)
    private String customerEmail;
    private LocalDateTime dateCreated;
    private LocalDateTime dateNotification;
    private boolean canBeNotified;
}
