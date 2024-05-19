package com.soa.orders.application.mapper;

import com.soa.orders.application.dtos.response.InventoryResponse;
import com.soa.orders.domain.models.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface IInventoryDtoMapper {
    InventoryResponse toResponse(Inventory inventory);
}
