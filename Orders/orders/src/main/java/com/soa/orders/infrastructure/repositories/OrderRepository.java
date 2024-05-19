package com.soa.orders.infrastructure.repositories;

import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.models.enums.Status;
import com.soa.orders.domain.port.IOrderProductRepository;
import com.soa.orders.domain.port.IOrderRepository;
import com.soa.orders.infrastructure.dao.OrderEntity;
import com.soa.orders.infrastructure.mappers.IOrderEntitiesMapper;
import com.soa.orders.infrastructure.repositories.jpa.IJpaOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepository implements IOrderRepository {
    @Autowired
    private IJpaOrderRepository jpaRepository;

    @Autowired
    private IOrderEntitiesMapper orderMapper;

    @Autowired
    private IOrderProductRepository orderProductRepository;

    @Override
    public Order updateOrderStatus(String uuid, String status) {
        OrderEntity orderEntity = jpaRepository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        orderEntity.setStatus(Status.valueOf(status));
        return orderMapper.toDomain(jpaRepository.save(orderEntity));
    }

    @Override
    public List<Order> getOrders() {
        return jpaRepository.findAll().stream().map(orderMapper::toDomain).toList();

    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        OrderEntity savedOrderEntity = jpaRepository.save(orderEntity);

        List<OrderProduct> savedOrderProducts = order.getProducts().stream()
        .map(product -> saveOrderProduct(product, savedOrderEntity.getUuid()))
        .toList();

        Order orderSave = orderMapper.toDomain(savedOrderEntity);
        orderSave.setProducts(savedOrderProducts);

        return orderSave;
    }

    @Override
    public OrderProduct saveOrderProduct(OrderProduct orderProduct, String orderUuid) {
        orderProduct.setOrderUuid(orderUuid);
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public Order findByUuid(String uuid) {
        return jpaRepository.findByUuid(uuid).map(orderMapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }
}
