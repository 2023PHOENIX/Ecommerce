package com.ecomm.inventory.consumers;

import com.ecomm.inventory.entity.Inventory;
import com.ecomm.inventory.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductCreationEventConsumer {

    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;

    /**
     * As this is used for INVENTORY.
     * We need only SKU CODE. used to save to inventory db.
     */
    @KafkaListener(topics = "product.created", groupId = "inventory-service")
    public void consume(@Payload String event) {
        log.atInfo().log("Received payload for product creation: {}", event);
        try {
            JsonNode node = objectMapper.readTree(event);
            String skuCode = node.get("skuCode").asText();
            log.atInfo().log("Processing SKU code: {}", skuCode);
            Inventory inventory = inventoryService.upsertInventory(skuCode);
            log.atInfo().log("Updated the inventory sku code {} with quantity: {}", inventory.getSkuCode(), inventory.getQuantity());
        } catch (JsonProcessingException e) { // throw a checked exception.
            log.atError().log("Failed to parse JSON event: {}", event, e);
            throw new RuntimeException("JSON processing error in consumer.", e);
        }
    }
}
