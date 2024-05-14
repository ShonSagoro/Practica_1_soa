package com.soa.orders.infrastructure.mappers;

import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.infrastructure.dao.OrderProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface IOrderProductEntitiesMapper {
    OrderProduct toDomain(OrderProductEntity orderProductEntity);
    OrderProductEntity toEntity(OrderProduct orderProduct);
}
