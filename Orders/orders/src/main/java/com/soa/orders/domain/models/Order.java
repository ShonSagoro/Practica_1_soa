package com.soa.orders.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Order {

    private Long id;

    private long total;

    private Date date;

    private String status;

}
