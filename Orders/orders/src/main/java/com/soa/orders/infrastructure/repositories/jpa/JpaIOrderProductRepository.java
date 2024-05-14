package com.soa.orders.infrastructure.repositories.jpa;

import com.soa.orders.infrastructure.dao.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIOrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
}
