package com.soa.orders.infrastructure.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "orders_products")
@Getter @Setter
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Change the generation strategy
    private Long id;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long product_id;

    @ManyToOne
    private OrderEntity order;
}
