package com.soa.orders.infrastructure.mappers;

import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.infrastructure.dao.OrderProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderProductEntitiesMapper {
    @Mapping(source = "order.uuid", target = "orderUuid")
    OrderProduct toDomain(OrderProductEntity orderProductEntity);
    OrderProductEntity toEntity(OrderProduct orderProduct);
}