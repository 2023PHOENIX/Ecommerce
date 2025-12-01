package com.ecomm.pct.service.strategy;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.enums.Category;
import com.ecomm.pct.service.strategy.annotation.CategoryMapping;
import org.springframework.stereotype.Component;

@Component
@CategoryMapping(Category.BOOKS)
public class BookProductStrategy implements ProductStrategy{
    @Override
    public void validate(CreationProductRequest productRequest) {
        if(!productRequest.getAttributes().containsKey("author")){
            throw new RuntimeException("books can't be there without authors");
        }
    }

    @Override
    public void enrich(CreationProductRequest productRequest) {
        productRequest.getAttributes().put("paperback",true);
    }
}
