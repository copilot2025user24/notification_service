package com.ey.notification_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {

    // Getters and Setters
    private String queueName;
    private String exchangeName;
    private String routingKey;
    private String emailQueueName;
    private String smsQueueName;
    private String emailExchangeName;
    private String smsExchangeName;

}
