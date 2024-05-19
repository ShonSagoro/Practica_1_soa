package com.soa.orders.domain.service;

import com.soa.orders.domain.models.Inventory;
import com.soa.orders.domain.models.OrderProduct;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IInventorySaga {
    CompletableFuture<List<Inventory>> getProduct(String uuid, List<OrderProduct> products);
//    void handleProductsDetails(List<Product> products);
}
