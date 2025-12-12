package com.ecomm.inventory.mapper;

import com.ecomm.inventory.dto.response.InventoryQueryResponse;
import com.ecomm.inventory.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryQueryResponse toResponse(Inventory inventory){
        return new InventoryQueryResponse(inventory.getSkuCode(),inventory.getQuantity());
    }
}
