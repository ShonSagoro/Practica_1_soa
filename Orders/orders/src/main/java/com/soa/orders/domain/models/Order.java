package com.soa.orders.domain.models;

import com.soa.orders.domain.models.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class Order {

    private Long id;

    private long total;

    private Date date;

    private Status status;

    private List<OrderProduct> products;

}
