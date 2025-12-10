package com.ecomm.inventory.service.impl;

import com.ecomm.inventory.dto.request.InventoryUpdateRequest;
import com.ecomm.inventory.entity.Inventory;
import com.ecomm.inventory.repository.InventoryRepository;
import com.ecomm.inventory.service.InventoryService;
import com.ecomm.inventory.service.commands.InventoryUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryUpdateService inventoryUpdateService;

    @Override
    public Inventory upsertInventory(String skuCode) {
        // fetch the sku code weather it is available or not right now ?
        // if it available then please update the existing object
        return inventoryRepository.findBySkuCode(skuCode)
                .map(existingInventory -> {
                    int newQuantity = existingInventory.getQuantity() + 1;
                    existingInventory.setQuantity(newQuantity);
                    return existingInventory;
                }).orElseGet(() -> {
                    Inventory inventory = new Inventory(skuCode);
                    inventory.setQuantity(1);
                    return inventoryRepository.save(inventory);
                });
    }

    public Inventory updateInventory(InventoryUpdateRequest inventoryUpdateRequest) {
         return inventoryUpdateService.execute(inventoryUpdateRequest).get();
    }


}
