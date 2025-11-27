package com.ecomm.pct.service.strategy;

import com.ecomm.pct.dto.request.CreationProductRequest;

public interface ProductStrategy {
    void validate(CreationProductRequest productRequest);
    void enrich(CreationProductRequest productRequest);
}
