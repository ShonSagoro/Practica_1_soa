package com.soa.orders.infrastructure.repositories.jpa;

import com.soa.orders.infrastructure.dao.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJpaOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByUuid(String uuid);
}
