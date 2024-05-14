package com.soa.orders.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderResponse {
    private Long id;
    private Long total;
    private String date;
    private String status;
}
