package com.soa.orders.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private String date;
    private List<CreateOrderProductRequest> products;
}
