package com.ecomm.order.grpc.client;

import com.ecomm.proto.inventory.CheckStockRequest;
import com.ecomm.proto.inventory.CheckStockResponse;
import com.ecomm.proto.inventory.InventoryServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class InventoryClient {

    @GrpcClient("inventory-service")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryServiceBlockingStub;

    public CheckStockResponse checkStock(String skuCode,Integer quantity) {
        CheckStockRequest request = CheckStockRequest.newBuilder()
                .setSku(skuCode)
                .setQuantity(quantity)
                .build();
        return inventoryServiceBlockingStub.checkStock(request);
    }
}
