package com.ecomm.pct.service;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.request.ProductQueryResponse;
import com.ecomm.pct.dto.response.ProductCreationResponse;

public interface ProductService {
    ProductCreationResponse createProduct(CreationProductRequest request);
    ProductQueryResponse getAllProduct(Integer pageNumber,Integer pageSize);
}
