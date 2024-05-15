package com.soa.orders.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderProduct {

    private String uuid;

    private Long price;

    private Long quantity;

    private String productUuid;

    private String orderUuid;

}
