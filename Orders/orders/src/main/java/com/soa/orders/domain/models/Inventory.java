package com.soa.orders.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Inventory {
    private String uuid;
    private String name;
    private Integer price;
    private Long stock;

    public Inventory(String uuid, String name, Integer price, Long stock) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
