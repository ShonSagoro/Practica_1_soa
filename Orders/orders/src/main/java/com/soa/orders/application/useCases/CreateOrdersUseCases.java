package com.soa.orders.application.useCases;

import com.soa.orders.application.dtos.mapper.IOrderDtoMapper;
import com.soa.orders.application.dtos.mapper.IOrderProductDtoMapper;
import com.soa.orders.application.dtos.request.CreateOrderProductRequest;
import com.soa.orders.application.dtos.request.CreateOrderRequest;
import com.soa.orders.application.dtos.response.BaseResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.models.enums.Status;
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

    @Autowired
    private IOrderDtoMapper orderDtoMapper;
    @Autowired
    private IOrderProductDtoMapper orderProductDtoMapper;


    public BaseResponse excuse(CreateOrderRequest request) {
        Order order = orderDtoMapper.toDomain(request);
        List<OrderProduct> products = request.getProducts().stream()
                .map(orderProductDtoMapper::toDomain)
                .toList();
        order.setTotal(getTotal(products));
        order.setProducts(products);
        products.forEach(orderProduct -> orderRepository.saveOrderProduct(orderProduct));
        orderRepository.save(order);
        OrderResponse orderResponse = orderDtoMapper.toResponse(order);

        return BaseResponse.builder()
                .data(orderResponse)
                .message("order created")
                .success(true)
                .httpStatus(org.springframework.http.HttpStatus.CREATED).build();
    }


    private Long getTotal(List<OrderProduct> products) {
        if (products.isEmpty()) {
            return 0L;
        }

        return products.stream()
                .mapToLong(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
}
