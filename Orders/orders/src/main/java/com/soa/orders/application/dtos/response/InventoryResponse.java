package com.soa.orders.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponse {
    private String uuid;
    private String name;
    private long price;
    private int stock;
}
