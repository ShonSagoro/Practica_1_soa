package com.soa.orders.infrastructure.controllers;

import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.useCases.GetOrdersUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GetOrdersController {
    @Autowired
    private GetOrdersUseCases getOrdersUseCases;

    @GetMapping
    public ResponseEntity<BaseResponse> get() {
        return getOrdersUseCases.excuse().apply();
    }
}
