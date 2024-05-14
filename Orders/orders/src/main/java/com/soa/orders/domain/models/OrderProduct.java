package com.soa.orders.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderProduct {

    private Long id;

    private Long price;

    private Long quantity;

    private Long product_id;

}
