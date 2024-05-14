package com.soa.orders.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateOrderStatusRequest {
    private String status;
}
