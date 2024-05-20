package com.soa.orders.application.mapper;

import com.soa.orders.application.dtos.request.CreateOrderRequest;
import com.soa.orders.application.dtos.response.InventoryResponse;
import com.soa.orders.application.dtos.response.OrderProductResponse;
import com.soa.orders.application.dtos.response.OrderResponse;
import com.soa.orders.domain.models.Inventory;
import com.soa.orders.domain.models.Order;
import com.soa.orders.domain.models.OrderProduct;
import com.soa.orders.domain.models.enums.Status;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IOrderProductDtoMapper.class, IInventoryDtoMapper.class})
public interface IOrderDtoMapper {
    @Mapping(target = "date", expression = "java(new java.util.Date())")
    @Mapping(target = "status", expression = "java(com.soa.orders.domain.models.enums.Status.PENDING)")
    Order toDomain(CreateOrderRequest orderEntity);

    @Mapping(source = "products", target = "products", qualifiedByName = "mapToOrderProductResponse")
    @Mapping(source = "inventories", target = "inventories", qualifiedByName = "MapToInventoriesResponse")
    OrderResponse toResponse(Order order);

    @IterableMapping(qualifiedByName = "mapToOrderProductResponse")
    List<OrderProductResponse> toResponseList(List<OrderProduct> orderProductList);
    @Named("mapToOrderProductResponse")
    OrderProductResponse mapToOrderProductResponse(OrderProduct orderProduct);

    @IterableMapping(qualifiedByName = "MapToInventoriesResponse")
    List<InventoryResponse> toResponseListInventories(List<Inventory> inventoryList);

    @Named("MapToInventoriesResponse")
    InventoryResponse MapToInventoriesResponse(Inventory inventory);

    default Status map(String status) {
        return Status.valueOf(status);
    }
}