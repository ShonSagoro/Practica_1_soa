package com.soa.orders.infrastructure.controllers;

import com.soa.orders.application.dtos.request.UpdateOrderStatusRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.useCases.UpdateOrderStatusUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UpdateOrderStatusController {
    @Autowired
    private UpdateOrderStatusUseCases updateOrderStatusUseCases;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest request) {
        return updateOrderStatusUseCases.excuse(id, request).apply();
    }
}
