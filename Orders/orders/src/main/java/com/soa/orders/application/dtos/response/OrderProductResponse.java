package com.soa.orders.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderProductResponse {
    private String uuid;

    private Long price;

    private Long quantity;

    private String productUuid;

    private String orderUuid;
}
