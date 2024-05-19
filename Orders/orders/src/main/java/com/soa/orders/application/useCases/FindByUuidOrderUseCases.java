package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.application.mapper.IOrderDtoMapper;
import com.soa.orders.domain.models.Inventory;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.port.IOrderRepository;
import com.soa.orders.domain.service.IInventorySaga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FindByUuidOrderUseCases {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IInventorySaga iInventorySaga;

    @Autowired
    private IOrderDtoMapper orderDtoMapper;

    public BaseResponse excuse(String uuid) {
        Order order = orderRepository.findByUuid(uuid);
        if (order != null) {
            List<Inventory> list = null;
            try {
                list = iInventorySaga.getProduct(order.getUuid(),order.getProducts()).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            order.setInventories(list);
            OrderResponse response = orderDtoMapper.toResponse(order);
            return BaseResponse.builder()
                    .data(order)
                    .message("order found")
                    .success(true)
                    .build();
        }else {
            return BaseResponse.builder()
                    .message("order not found")
                    .success(false)
                    .build();

        }

    }

}
