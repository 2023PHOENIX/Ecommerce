package com.ecomm.pct.service.impl;


import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.request.ProductQueryResponse;
import com.ecomm.pct.dto.response.ProductCreationResponse;
import com.ecomm.pct.service.ProductService;
import com.ecomm.pct.service.command.ProductCreationalService;
import com.ecomm.pct.service.query.ProductQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductCreationalService productCreationalService;
    private final ProductQueryService productQueryService;

    @Override
    public ProductCreationResponse createProduct(CreationProductRequest request) {
        return productCreationalService.execute(request).get();
    }

    @Override
    public ProductQueryResponse getAllProduct(Integer pageNumber, Integer pageSize) {
        log.atInfo().log("inside the service impl for fetching all products");
        return productQueryService.fetchAll(pageNumber,pageSize);
    }

}
