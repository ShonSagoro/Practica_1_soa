package com.soa.orders.application.dtos.response;

import com.soa.orders.domain.models.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindByUuidSagaResponse {
    private String uuid;
    private List<Inventory> products;
}
