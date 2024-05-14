package com.soa.orders.infrastructure.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter @Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long total;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderProductEntity> order_products;
}
