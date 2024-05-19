package com.soa.orders.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FindByUuidSagaRequest {
    private String uuid;
    private List<String> inventories;
}
