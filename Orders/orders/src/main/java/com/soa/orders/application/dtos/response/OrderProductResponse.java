package com.soa.orders.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderProductResponse {
    private Long id;

    private Long price;

    private Long quantity;

    private Long product_id;
}
