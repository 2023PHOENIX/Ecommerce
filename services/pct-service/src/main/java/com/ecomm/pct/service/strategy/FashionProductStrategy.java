package com.ecomm.pct.service.strategy;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.enums.Category;
import com.ecomm.pct.service.strategy.annotation.CategoryMapping;
import org.springframework.stereotype.Component;

@Component
@CategoryMapping(Category.FASHION)
public class FashionProductStrategy implements ProductStrategy {
    @Override
    public void validate(CreationProductRequest productRequest) {
        if (!productRequest.getAttributes().containsKey("size")) {
            throw new RuntimeException("Fashion product must have size");
        }
    }

    @Override
    public void enrich(CreationProductRequest productRequest) {
        productRequest.getAttributes().put("season", "2025");
    }
}
