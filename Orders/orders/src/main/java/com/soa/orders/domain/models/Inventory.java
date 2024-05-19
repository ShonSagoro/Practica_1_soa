package com.soa.orders.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Inventory {
    private String uuid;
    private String name;
    private long price;
    private int stock;
}
