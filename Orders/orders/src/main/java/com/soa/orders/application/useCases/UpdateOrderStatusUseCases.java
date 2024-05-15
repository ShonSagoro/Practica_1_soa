package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.mapper.IOrderDtoMapper;
import com.soa.orders.application.dtos.request.UpdateOrderStatusRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.port.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusUseCases {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderDtoMapper orderDtoMapper;

    public BaseResponse excuse(Long id, UpdateOrderStatusRequest request) {
        Order order = orderRepository.updateOrderStatus(id, request.getStatus());
        OrderResponse response = orderDtoMapper.toResponse(order);
        return BaseResponse.builder()
                .data(response)
                .message("order status updated")
                .success(true)
                .httpStatus(HttpStatus.OK).build();
    }
}
