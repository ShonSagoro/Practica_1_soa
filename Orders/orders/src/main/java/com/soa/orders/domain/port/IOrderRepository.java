package com.soa.orders.domain.port;

import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;

import java.util.List;

public interface IOrderRepository {
    Order updateOrderStatus(String uuid, String status);
    List<Order> getOrders();
    Order save(Order order);
    OrderProduct saveOrderProduct(OrderProduct orderProduct, String orderUuid);
    Order findByUuid(String uuid);
}
