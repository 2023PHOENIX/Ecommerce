package com.ecomm.pct.service;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(CreationProductRequest request);
}
