package com.ey.notification_service.event.messaging;

import com.ey.notification_service.config.RabbitMQProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQProperties properties;

    public void sendSMS(Message message) {
        System.out.println("Sending SMS message: " + message);
        rabbitTemplate.convertAndSend(properties.getSmsQueueName(), message);
    }
}
