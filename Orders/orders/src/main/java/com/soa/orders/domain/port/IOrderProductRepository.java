package com.soa.orders.domain.port;

import com.soa.orders.domain.models.OrderProduct;

public interface IOrderProductRepository {
    OrderProduct save(OrderProduct orderProduct);
}
