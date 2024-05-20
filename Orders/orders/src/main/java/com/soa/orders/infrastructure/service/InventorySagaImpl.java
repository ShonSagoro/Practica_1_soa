package com.soa.orders.infrastructure.service;

import com.soa.orders.application.dtos.request.FindByUuidSagaRequest;
import com.soa.orders.application.dtos.response.FindByUuidSagaResponse;
import com.soa.orders.domain.models.Inventory;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.service.IInventorySaga;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InventorySagaImpl implements IInventorySaga {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name.requests}")
    private String inventoryExchange;

    @Value("${rabbitmq.routing.key.inventory.request}")
    private String inventoryReqRoutingKey;

    private final ConcurrentHashMap<String, CompletableFuture<List<Inventory>>> futures = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<List<Inventory>> getProduct(String uuid, List<OrderProduct> products) {
        CompletableFuture<List<Inventory>> future = new CompletableFuture<>();
        List<String> productUuids = products.stream().map(OrderProduct::getProductUuid).toList();
        futures.put(uuid, future);
        FindByUuidSagaRequest request = new FindByUuidSagaRequest();
        request.setUuid(uuid);
        request.setInventories(productUuids);
        rabbitTemplate.convertAndSend(inventoryExchange, inventoryReqRoutingKey, request);
        return future;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name.inventory.response}")
    public void handleProductsDetails(FindByUuidSagaResponse response) {
        CompletableFuture<List<Inventory>> future = futures.get(response.getUuid());
        future.complete(response.getProducts());
    }
}
