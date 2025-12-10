package com.ecomm.inventory.service.commands;

import com.ecomm.inventory.dto.request.InventoryUpdateRequest;
import com.ecomm.inventory.entity.Inventory;
import com.ecomm.inventory.enums.InventoryOperation;
import com.ecomm.inventory.repository.InventoryRepository;
import com.ecomm.railway.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryUpdateService {

    private final InventoryRepository inventoryRepository;

    public Result<Inventory> execute(InventoryUpdateRequest inventoryUpdateRequest) {
        // get the inventory
        log.atInfo().log("inside the inventory update");
        return Result.success(inventoryUpdateRequest.skuCode())
                .bind(this::loadInventory)
                .bind((inventory) -> applyOperation(inventory, inventoryUpdateRequest.quantity(), inventoryUpdateRequest.inventoryOperation()))
                .bind(this::saveToDB);

    }

    private Result<Inventory> loadInventory(String skuCode) {
        log.atInfo().log("inventory loading");
        return inventoryRepository.findBySkuCode(skuCode)
                .map(Result::success)
                .orElseGet(() -> Result.failure("Inventory not found"));
    }

    private Result<Inventory> applyOperation(Inventory inventory, int quantity, InventoryOperation inventoryOperation) {

        try {
            if (Objects.requireNonNull(inventoryOperation) == InventoryOperation.ADD) {
                inventory.setQuantity(inventory.getQuantity() + quantity);
            } else if (Objects.requireNonNull(inventoryOperation) == InventoryOperation.REMOVE) {
                inventory.setQuantity(inventory.getQuantity() - quantity);
            }else if(Objects.requireNonNull(inventoryOperation) == InventoryOperation.SET){
                if(quantity <= 0){
                    Result.failure("Quantity cannot be less than 0");
                }else{
                    inventory.setQuantity(quantity);
                }
            }
            return Result.success(inventory);
        } catch (Exception e) {
            log.atInfo().log("Error applying inventory operation {}",e.getMessage());
            return Result.failure(e.getMessage());
        }
    }

    private Result<Inventory> saveToDB(Inventory inventory){
        inventoryRepository.save(inventory);
        return Result.success(inventory);
    }
}
