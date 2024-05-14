package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.request.CreateOrderProductRequest;
import com.soa.orders.application.dtos.request.CreateOrderRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.port.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CreateOrdersUseCases {
    @Autowired
    private IOrderRepository orderRepository;


    public BaseResponse excuse(CreateOrderRequest request) {
        Order order = toOrder(request);
        List<OrderProduct> list = request.getProducts().stream()
                .map(this::toOrderProduct)
                .toList();
        list.forEach(orderProduct -> orderRepository.saveOrderProduct(orderProduct));

        return BaseResponse.builder()
                .data(orderRepository.save(order))
                .message("order created")
                .success(true)
                .httpStatus(org.springframework.http.HttpStatus.CREATED).build();
    }

    private OrderProduct toOrderProduct(CreateOrderProductRequest product) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct_id(product.getProduct_id());
        orderProduct.setPrice(product.getPrice());
        orderProduct.setQuantity(product.getQuantity());
        return orderProduct;
    }

    private Order toOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setDate(toDate(request.getDate()));
        order.setStatus("pending");
        order.setTotal(getTotal(request));
        return order;
    }

    private Long getTotal(CreateOrderRequest request) {
        if (request.getProducts().isEmpty()) {
            return 0L;
        }

        return request.getProducts().stream()
                .mapToLong(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    private Date toDate(String date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
