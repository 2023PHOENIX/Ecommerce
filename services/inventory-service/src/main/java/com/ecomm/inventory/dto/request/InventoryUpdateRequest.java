package com.ecomm.inventory.dto.request;

import com.ecomm.inventory.enums.InventoryOperation;
import jakarta.validation.constraints.NotNull;

public record InventoryUpdateRequest(@NotNull String skuCode,@NotNull int quantity, @NotNull InventoryOperation inventoryOperation) {
}
