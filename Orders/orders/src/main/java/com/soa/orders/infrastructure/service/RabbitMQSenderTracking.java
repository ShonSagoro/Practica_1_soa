package com.soa.orders.infrastructure.service;

import com.google.gson.Gson;
import com.soa.orders.domain.service.RabbitMQSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSenderTracking implements RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    public void MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Object message) {
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonMessage);
    }
}
