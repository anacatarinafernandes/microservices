package com.anafernandes.notification;

import com.anafernandes.notification.model.Notification;
import com.anafernandes.notification.repository.NotificationRepository;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;

@Service
public class NotificationJobService {
    private final NotificationRepository notificationRepository;

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
            System.out.println("Sending notification to " + email);

        }

    }
}
