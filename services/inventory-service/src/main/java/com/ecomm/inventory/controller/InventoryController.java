package com.ecomm.inventory.controller;

import com.ecomm.inventory.dto.request.InventoryUpdateRequest;
import com.ecomm.inventory.dto.response.InventoryQueryResponse;
import com.ecomm.inventory.entity.Inventory;
import com.ecomm.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PutMapping
    public boolean updateInventory(@RequestBody @Valid InventoryUpdateRequest inventoryUpdateRequest) {
        Inventory inventory = inventoryService.updateInventory(inventoryUpdateRequest);
        return inventory != null;
    }

    @GetMapping("/{skuCode}")
    public InventoryQueryResponse getInventory(@PathVariable String skuCode) {
        return inventoryService.getInventory(skuCode);
    }
}
