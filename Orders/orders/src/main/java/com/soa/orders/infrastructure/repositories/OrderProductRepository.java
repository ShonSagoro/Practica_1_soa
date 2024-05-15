package com.soa.orders.infrastructure.repositories;

import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.port.IOrderProductRepository;
import com.soa.orders.domain.port.IOrderRepository;
import com.soa.orders.infrastructure.dao.OrderProductEntity;
import com.soa.orders.infrastructure.mappers.IOrderEntitiesMapper;
import com.soa.orders.infrastructure.mappers.IOrderProductEntitiesMapper;
import com.soa.orders.infrastructure.repositories.jpa.IJpaOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrderProductRepository implements IOrderProductRepository {
    @Autowired
    private IJpaOrderProductRepository jpaRepository;

    @Autowired
    private IOrderProductEntitiesMapper orderProductMapper;

    @Autowired
    private IOrderEntitiesMapper orderMapper;

    @Autowired
    @Lazy
    private IOrderRepository orderRepository;

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        OrderProductEntity orderProductEntity = orderProductMapper.toEntity(orderProduct);
        orderProductEntity.setOrder(orderMapper.toEntity(orderRepository.findByUuidOrder(orderProduct.getOrderUuid())));
        return orderProductMapper.toDomain(jpaRepository.save(orderProductEntity));
    }
}
