package com.ecomm.order.controller;

import com.ecomm.order.grpc.client.InventoryClient;
import com.ecomm.proto.inventory.CheckStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/inventory")
@RequiredArgsConstructor
public class OrderController {

    private final InventoryClient inventoryClient;


    @GetMapping("/order/{skuCode}/{quantity}")
    public String checkStock(@PathVariable String skuCode, @PathVariable Integer quantity){
        CheckStockResponse stockResponse = inventoryClient.checkStock(skuCode,quantity);
        return stockResponse.toString();
    }

}
