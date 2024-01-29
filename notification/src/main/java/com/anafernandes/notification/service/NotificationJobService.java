package com.anafernandes.notification.service;

import com.anafernandes.notification.model.Notification;
import com.anafernandes.notification.repository.NotificationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;

@Service
public class NotificationJobService {
    private final NotificationRepository notificationRepository;

    private static final Logger logger = LogManager.getLogger(NotificationJobService.class);


    public NotificationJobService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    //@Job(name = "The sample job with variable %0", retries = 2)
    @Recurring(id = "notification-job", cron = "*/1 * * * *")
    @Job(name = "Notification job", retries = 10)
    public void sendNotificationJob() {

        var notifications = notificationRepository.getNotificationsByCanBeNotified(true);

        for (Notification notification : notifications) {

            String email = notification.getCustomerEmail();
            logger.info("Sending notification to " + email);

        }

    }
}
