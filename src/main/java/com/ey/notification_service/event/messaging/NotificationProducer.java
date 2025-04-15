package com.ey.notification_service.event.messaging;

import com.ey.notification_service.config.RabbitMQProperties;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class NotificationProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQProperties properties;


    public void sendNotificationEvent(Message message) {
        System.out.println("Sending message: " + message);
        rabbitTemplate.convertAndSend(properties.getExchangeName(), properties.getRoutingKey(), message);
    }
}