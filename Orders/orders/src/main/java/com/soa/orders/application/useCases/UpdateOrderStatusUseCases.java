package com.soa.orders.application.useCases;

import com.soa.orders.application.mapper.IOrderDtoMapper;
import com.soa.orders.application.dtos.request.UpdateOrderStatusRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.enums.Status;
import com.soa.orders.domain.port.IOrderRepository;
import com.soa.orders.domain.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusUseCases {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderDtoMapper orderDtoMapper;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    public BaseResponse excuse(String uuid, UpdateOrderStatusRequest request) {
        Order order = orderRepository.updateOrderStatus(uuid, request.getStatus());
        OrderResponse response = orderDtoMapper.toResponse(order);
        if (response.getStatus().equals(Status.SENT.name())){
            rabbitMQSender.sendMessage(response);
        }
        return BaseResponse.builder()
                .data(response)
                .message("order status updated")
                .success(true)
                .httpStatus(HttpStatus.OK).build();
    }
}
