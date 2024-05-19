package com.soa.orders.infrastructure.service;

import com.soa.orders.domain.service.ITrackingSaga;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TrackingSagaImpl implements ITrackingSaga {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name.tracking}")
    private String orderExchange;

    @Value("${rabbitmq.routing.key.tracking}")
    private String OrderRoutingKey;

    @Override
    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend(orderExchange, OrderRoutingKey, message);
    }
}
