package com.soa.orders.infrastructure.controllers;

import com.soa.orders.application.dtos.request.CreateOrderRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.useCases.CreateOrdersUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CreateOrdersController {
    @Autowired
    private CreateOrdersUseCases createOrdersUseCases;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateOrderRequest request) {
        return createOrdersUseCases.excuse(request).apply();
    }
}
