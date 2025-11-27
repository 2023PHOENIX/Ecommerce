package com.ecomm.pct.service.strategy;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.enums.Category;
import com.ecomm.pct.service.strategy.annotation.CategoryMapping;
import org.springframework.stereotype.Component;

@Component
@CategoryMapping(Category.ELECTRONICS)
public class ElectronicProductStrategy implements ProductStrategy{
    @Override
    public void validate(CreationProductRequest productRequest) {
        if(!productRequest.getAttributes().containsKey("warranty")){
           throw new RuntimeException("Electronic must have warranty");
        }
    }

    @Override
    public void enrich(CreationProductRequest productRequest) {
        productRequest.getAttributes().put("type","ELECTRONIC");
    }
}
