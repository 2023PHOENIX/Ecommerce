package com.ecomm.inventory.grpc.server;

import com.ecomm.inventory.dto.response.InventoryQueryResponse;
import com.ecomm.inventory.service.InventoryService;
import com.ecomm.proto.inventory.CheckStockRequest;
import com.ecomm.proto.inventory.CheckStockResponse;
import com.ecomm.proto.inventory.InventoryServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class InventoryGrpcService extends InventoryServiceGrpc.InventoryServiceImplBase {

    private final InventoryService inventoryService;

    @Override
    public void checkStock(CheckStockRequest request, StreamObserver<CheckStockResponse> responseStreamObserver) {
        log.atInfo().log("Received request for the product {} with quantity {}", request.getSku(), request.getQuantity());

        InventoryQueryResponse queryResponse = inventoryService.getInventory(request.getSku());


        CheckStockResponse response = CheckStockResponse.newBuilder()
                .setAvailable(request.getQuantity() <= queryResponse.quantity())
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }


}
