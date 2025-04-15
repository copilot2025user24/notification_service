package com.ey.notification_service.model;

import lombok.Data;

import java.util.List;

@Data
public class NotificationMessagePayload {
    private String messagePayload;
    private List<Long> mobileNumbers;
    private List<String> emailIds;
    private String messageType;
}