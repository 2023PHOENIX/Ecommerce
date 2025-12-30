package com.ecomm.pct.service.query;

import com.ecomm.pct.dto.request.ProductDetailsResponse;
import com.ecomm.pct.dto.request.ProductQueryResponse;
import com.ecomm.pct.entity.ProductEntity;
import com.ecomm.pct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductQueryService {

    final ProductRepository productRepository;

    public ProductQueryResponse fetchAll(Integer pageNumber, Integer pageSize) {
        log.atInfo().log("Fetching products - Page: {}, Page Size: {}", pageNumber, pageSize);
        try {

            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<ProductEntity> productEntityList = productRepository.findAll(pageable);
            List<ProductDetailsResponse> productQueryResponseList = productEntityList.getContent().stream()
                    .map(entity ->
                            new ProductDetailsResponse(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getCategory())
                    ).toList();
            return new ProductQueryResponse(productQueryResponseList);
        } catch (Exception e) {
            log.atError().log("Error fetching products: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
