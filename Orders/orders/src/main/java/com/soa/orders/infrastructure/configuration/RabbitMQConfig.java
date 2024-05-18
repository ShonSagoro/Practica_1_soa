package com.soa.orders.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name.tracking}")
    private String inventoryQueue;

    @Value("${rabbitmq.exchange.name.tracking}")
    private String orderExchange;

    @Value("${rabbitmq.routing.key.tracking}")
    private String orderRoutingKey;

    @Bean
    public Queue InventoryQueue() {
        return new Queue(inventoryQueue, true);
    }

    @Bean
    public DirectExchange OrderExchange() {
        return new DirectExchange(orderExchange);
    }

    @Bean
    public Binding OrderBinding() {
        return BindingBuilder.bind(InventoryQueue()).to(OrderExchange()).with(orderRoutingKey);
    }
}