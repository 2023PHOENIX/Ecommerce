package com.ecomm.pct.service.factory;

import com.ecomm.pct.enums.Category;
import com.ecomm.pct.service.strategy.ProductStrategy;
import com.ecomm.pct.service.strategy.annotation.CategoryMapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 This factory provide us which strategy we need to use at runtime.
 */

@Component
public class ProductStrategyFactory {

    private final Map<Category, ProductStrategy> strategyMap;

    /**
     * Reflection api help find all the strategies implementations.
     * again Reflection api help us find the annotations also
     * @param strategies
     */
    public ProductStrategyFactory(List<ProductStrategy> strategies){
        this.strategyMap = strategies.stream().collect(Collectors.toMap(
                s -> s.getClass().getAnnotation(CategoryMapping.class).value(),
                s -> s
        ));
    }

    public ProductStrategy get(Category category){
        return strategyMap.get(category);
    }

}
