package com.soa.orders.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderProductRequest {

    private long price;

    private long quantity;

    private long product_id;

}
