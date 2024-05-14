package com.soa.orders.infrastructure.repositories;

import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.port.IOrderProductRepository;
import com.soa.orders.domain.port.IOrderRepository;
import com.soa.orders.infrastructure.dao.OrderEntity;
import com.soa.orders.infrastructure.mappers.IOrderEntitiesMapper;
import com.soa.orders.infrastructure.repositories.jpa.JpaIOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepository implements IOrderRepository {
    @Autowired
    private JpaIOrderRepository jpaRepository;

    @Autowired
    private IOrderEntitiesMapper orderMapper;

    @Autowired
    private IOrderProductRepository orderProductRepository;

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        OrderEntity orderEntity = jpaRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        orderEntity.setStatus(status);
        return orderMapper.toDomain(jpaRepository.save(orderEntity));
    }

    @Override
    public List<Order> getOrders() {
        return jpaRepository.findAll().stream().map(orderMapper::toDomain).toList();

    }
    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        return orderMapper.toDomain(jpaRepository.save(orderEntity));
    }

    @Override
    public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }
}
