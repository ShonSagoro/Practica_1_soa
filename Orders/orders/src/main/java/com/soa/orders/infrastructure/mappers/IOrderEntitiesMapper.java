package com.soa.orders.infrastructure.mappers;

import com.soa.orders.application.dtos.response.OrderProductResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.infrastructure.dao.OrderEntity;
import com.soa.orders.infrastructure.dao.OrderProductEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = IOrderProductEntitiesMapper.class)
public interface IOrderEntitiesMapper {
    Order toDomain(OrderEntity orderEntity);

    OrderEntity toEntity(Order order);

    @IterableMapping(elementTargetType = OrderProductEntity.class)
    List<OrderProductResponse> toResponseList(List<OrderProduct> orderProductList);

}
