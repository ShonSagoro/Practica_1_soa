package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.request.UpdateOrderStatusRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.port.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusUseCases {
    @Autowired
    private IOrderRepository orderRepository;

    public BaseResponse excuse(Long id, UpdateOrderStatusRequest request) {
        Order order = orderRepository.updateOrderStatus(id, request.getStatus());
        return BaseResponse.builder()
                .data(order)
                .message("order status updated")
                .success(true)
                .httpStatus(HttpStatus.OK).build();
    }
}
