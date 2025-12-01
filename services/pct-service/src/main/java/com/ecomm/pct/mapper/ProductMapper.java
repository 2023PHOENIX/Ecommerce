package com.ecomm.pct.mapper;

import com.ecomm.events.ProducerCreateEvent;
import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.response.ProductResponse;
import com.ecomm.pct.entity.ProductEntity;
import com.ecomm.pct.enums.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /**
     * converts the creational product request to product entity for db to save
     *
     * @param productRequest
     * @return converted to *productEntity*
     */
    public ProductEntity toEntity(CreationProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setCategory(Category.valueOf(productRequest.getCategory().toUpperCase()));
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setAttributes(productRequest.getAttributes());
        return productEntity;
    }

    /**
     * this will convert the product entity to response
     *
     * @param productEntity
     * @return the product response entity
     */
    public ProductResponse toResponse(ProductEntity productEntity) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(productEntity.getName());
        productResponse.setDescription(productEntity.getDescription());
        productResponse.setCategory(productEntity.getCategory().toString());
        productResponse.setPrice(productEntity.getPrice());
        productResponse.setCategory(productEntity.getCategory().toString());
        productResponse.setAttributes(productEntity.getAttributes());
        return productResponse;
    }

    public ProducerCreateEvent toEvent(ProductEntity productEntity) {
        ProducerCreateEvent producerCreateEvent = new ProducerCreateEvent();
        producerCreateEvent.setProductId(productEntity.getId().toString());
        producerCreateEvent.setName(productEntity.getName());
        producerCreateEvent.setPrice(productEntity.getPrice());
        producerCreateEvent.setCategory(productEntity.getCategory().toString());
        return producerCreateEvent;
    }
}
