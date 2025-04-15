package com.ey.notification_service.controller;

import com.ey.notification_service.event.messaging.NotificationProducer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {

    @Autowired
    NotificationProducer notificationProducer;

    @GetMapping("/health-check")
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<>("Health is up", HttpStatus.OK);
    }

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody String message) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("message_id", UUID.randomUUID());
        headers.put("mobile_number", "9658726574");
        headers.put("mail_id", "rajat.sahoo@gmail.com");
        headers.put("notification_type", Arrays.asList("SMS", "EMAIL"));

        Message notificationMessage = MessageBuilder.withBody(message.getBytes())
                .copyHeaders(headers)
                .build();

        notificationProducer.sendNotificationEvent(notificationMessage);
        return new ResponseEntity<>("Message published", HttpStatus.OK);
    }

}