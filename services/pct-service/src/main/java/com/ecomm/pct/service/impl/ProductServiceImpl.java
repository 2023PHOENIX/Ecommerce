package com.ecomm.pct.service.impl;


import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.response.ProductResponse;
import com.ecomm.pct.service.ProductService;
import com.ecomm.pct.service.command.ProductCreationalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductCreationalService productCreationalService;

    @Override
    public ProductResponse createProduct(CreationProductRequest request) {
        return productCreationalService.execute(request).get();
    }


}
