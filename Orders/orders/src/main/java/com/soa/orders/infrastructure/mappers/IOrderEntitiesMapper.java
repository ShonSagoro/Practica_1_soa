package com.soa.orders.infrastructure.mappers;

import com.soa.orders.domain.models.Order;
import com.soa.orders.infrastructure.dao.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderEntitiesMapper {
    Order toDomain(OrderEntity orderEntity);
    OrderEntity toEntity(Order order);
}
