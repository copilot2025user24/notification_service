package com.ey.notification_service.event.messaging;

import com.ey.notification_service.config.RabbitMQProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NotificationConsumer {

    @Autowired
    SMSProducer smsProducer;

    @Autowired
    EmailProducer emailProducer;

    @RabbitListener(queues = "${rabbitmq.queue-name}")
    public void receiveNotificationEvent(Message message) {
        System.out.println("Received message: " + message);
        List<String> notificationType = message.getMessageProperties().getHeader("notification_type");
        for (String type : notificationType) {
            if ("SMS".equalsIgnoreCase(type)) {
                smsProducer.sendSMS(message);
            } else if ("EMAIL".equalsIgnoreCase(type)) {
                emailProducer.sendEmail(message);
            } else {
                System.out.println("Unknown notification type: " + notificationType);
            }
        }
    }
}