package com.soa.orders.infrastructure.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "orders_products")
@Getter @Setter
public class OrderProductEntity {
    @Id
    @UuidGenerator
    private String uuid;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private String productUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_uuid", nullable = false)
    private OrderEntity order;
}
