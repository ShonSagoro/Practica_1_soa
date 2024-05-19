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

    @Value("${rabbitmq.exchange.name.tracking}")
    private String orderExchange;

    @Value("${rabbitmq.queue.name.tracking}")
    private String inventoryQueue;

    @Value("${rabbitmq.routing.key.tracking}")
    private String orderRoutingKey;

    @Value("${rabbitmq.exchange.name.requests}")
    private String inventoryExchange;

    @Value("${rabbitmq.queue.name.inventory.request}")
    private String inventoryReqQueue;

    @Value("${rabbitmq.routing.key.inventory.request}")
    private String inventoryReqRoutingKey;

    @Value("${rabbitmq.queue.name.inventory.response}")
    private String inventoryResQueue;

    @Value("${rabbitmq.routing.key.inventory.response}")
    private String inventoryResRoutingKey;


    @Bean
    public DirectExchange OrderExchange() {
        return new DirectExchange(orderExchange);
    }

    @Bean
    public DirectExchange InventoryExchange() {
        return new DirectExchange(inventoryExchange);
    }

    @Bean
    public Queue InventoryQueue() {
        return new Queue(inventoryQueue, true);
    }

    @Bean
    public Queue InventoryReqQueue() {
        return new Queue(inventoryReqQueue, true);
    }

    @Bean
    public Queue InventoryResQueue() {
        return new Queue(inventoryResQueue, true);
    }

    @Bean
    public Binding OrderBinding() {
        return BindingBuilder.bind(InventoryQueue()).to(OrderExchange()).with(orderRoutingKey);
    }

    @Bean
    public Binding InventoryReqBinding() {
        return BindingBuilder.bind(InventoryReqQueue()).to(InventoryExchange()).with(inventoryReqRoutingKey);
    }

    @Bean
    public Binding InventoryResBinding() {
        return BindingBuilder.bind(InventoryResQueue()).to(InventoryExchange()).with(inventoryResRoutingKey);
    }
}