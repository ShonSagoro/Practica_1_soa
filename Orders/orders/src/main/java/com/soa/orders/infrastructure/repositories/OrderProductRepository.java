package com.soa.orders.infrastructure.repositories;

import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.port.IOrderProductRepository;
import com.soa.orders.infrastructure.mappers.IOrderProductEntitiesMapper;
import com.soa.orders.infrastructure.repositories.jpa.JpaIOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProductRepository implements IOrderProductRepository {
    @Autowired
    private JpaIOrderProductRepository jpaRepository;

    @Autowired
    private IOrderProductEntitiesMapper orderProductMapper;

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        return orderProductMapper.toDomain(jpaRepository.save(orderProductMapper.toEntity(orderProduct)));
    }
}
