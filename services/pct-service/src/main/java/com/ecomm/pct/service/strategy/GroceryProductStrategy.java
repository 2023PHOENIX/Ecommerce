package com.ecomm.pct.service.strategy;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.enums.Category;
import com.ecomm.pct.service.strategy.annotation.CategoryMapping;
import org.springframework.stereotype.Component;

@Component
@CategoryMapping(Category.GROCERY)
public class GroceryProductStrategy implements ProductStrategy{
    @Override
    public void validate(CreationProductRequest productRequest) {
        if(!productRequest.getAttributes().containsKey("expiryDate")){
            throw new RuntimeException("Grocery must include expiry date");
        }
    }

    @Override
    public void enrich(CreationProductRequest productRequest) {
        productRequest.getAttributes().put("fresh",true);
    }
}
