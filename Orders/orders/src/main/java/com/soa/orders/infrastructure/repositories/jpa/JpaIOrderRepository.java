package com.soa.orders.infrastructure.repositories.jpa;

import com.soa.orders.domain.models.Order;
import com.soa.orders.infrastructure.dao.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIOrderRepository extends JpaRepository<OrderEntity, Long> {
}
