package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.application.mapper.IOrderDtoMapper;
import com.soa.orders.domain.models.Inventory;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.port.IOrderRepository;
import com.soa.orders.domain.service.IInventorySaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
            List<Inventory> list = iInventorySaga.getProduct(order.getUuid(),order.getProducts()).join();
            order.setInventories(list);
            OrderResponse response = orderDtoMapper.toResponse(order);
            return BaseResponse.builder()
                    .data(response)
                    .message("order found")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        }else {
            return BaseResponse.builder()
                    .data(null)
                    .message("order not found")
                    .success(false)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

}
