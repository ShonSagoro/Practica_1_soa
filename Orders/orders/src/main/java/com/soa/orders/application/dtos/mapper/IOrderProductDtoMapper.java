package com.soa.orders.application.dtos.mapper;

import com.soa.orders.application.dtos.request.CreateOrderProductRequest;
import com.soa.orders.application.dtos.response.OrderProductResponse;
import com.soa.orders.domain.models.OrderProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderProductDtoMapper {
    OrderProduct toDomain(CreateOrderProductRequest orderEntity);
    OrderProductResponse toResponse(OrderProduct order);
}
