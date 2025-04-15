package com.ey.notification_service.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    RabbitMQProperties properties;


    @Bean
    public Queue queue() {
        return new Queue(properties.getQueueName(), true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(properties.getEmailQueueName(), true);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(properties.getSmsQueueName(), true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(properties.getExchangeName());
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
    }
}