package com.soa.orders.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderProductRequest {

    private Long price;

    private Long quantity;

    private Long product_id;

}
