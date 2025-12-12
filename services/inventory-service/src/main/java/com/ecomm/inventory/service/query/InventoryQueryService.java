package com.ecomm.inventory.service.query;

import com.ecomm.inventory.entity.Inventory;
import com.ecomm.inventory.mapper.InventoryMapper;
import com.ecomm.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryQueryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public Inventory getBySkuCode(String skuCode) {

        return inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("skuCode not found"));
    }
}
