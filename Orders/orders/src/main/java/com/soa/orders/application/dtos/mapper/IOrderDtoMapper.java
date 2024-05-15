package com.soa.orders.application.dtos.mapper;

import com.soa.orders.application.dtos.request.CreateOrderRequest;
import com.soa.orders.application.dtos.response.OrderProductResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.models.enums.Status;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = IOrderProductDtoMapper.class)

public interface IOrderDtoMapper {
    @Mapping(target = "date", expression = "java(new java.util.Date())")
    @Mapping(target = "status", expression = "java(com.soa.orders.domain.models.enums.Status.PENDING)")
    Order toDomain(CreateOrderRequest orderEntity);

    OrderResponse toResponse(Order order);

    @IterableMapping(elementTargetType = OrderProductResponse.class)
    List<OrderProductResponse> toResponseList(List<OrderProduct> orderProductList);

    default Status map(String status) {
        return Status.valueOf(status);
    }
}
