package com.soa.orders.domain.service;

public interface RabbitMQSender {
    void sendMessage(Object message);
}
