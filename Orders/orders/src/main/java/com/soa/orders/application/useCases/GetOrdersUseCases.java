package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.port.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersUseCases {
    @Autowired
    private IOrderRepository orderRepository;

    public BaseResponse excuse() {
        List<Order> orders = orderRepository.getOrders();
        List<OrderResponse> responses = orders.stream().map(this::toResponse).toList();
        if (orders.isEmpty()) {
            return BaseResponse.builder()
                    .data(responses)
                    .message("no orders found")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND).build();
        }else{
            return BaseResponse.builder()
                    .data(responses)
                    .message("find all orders")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND).build();
        }
    }

    private OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setDate(order.getDate().toString());
        response.setStatus(order.getStatus());
        response.setTotal(order.getTotal());
        return response;
    }
}
