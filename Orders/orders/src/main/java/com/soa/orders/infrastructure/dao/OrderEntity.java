package com.soa.orders.infrastructure.dao;

import com.soa.orders.domain.models.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter @Setter
public class OrderEntity {
    @Id
    @UuidGenerator
    private String uuid;

    @Column(nullable = false)
    private long total;

    @Column(nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order")
    private List<OrderProductEntity> products;
}
