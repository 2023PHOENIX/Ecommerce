package com.ecomm.pct.service.impl;


import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.response.ProductResponse;
import com.ecomm.pct.entity.ProductEntity;
import com.ecomm.pct.enums.Category;
import com.ecomm.pct.mapper.ProductMapper;
import com.ecomm.pct.repository.ProductRepository;
import com.ecomm.pct.service.ProductService;
import com.ecomm.pct.service.factory.ProductStrategyFactory;
import com.ecomm.railway.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductStrategyFactory factory;

    @Override
    public ProductResponse createProduct(CreationProductRequest request) {
        log.atInfo().log("inside the create product service");
        return Result.success(request)
                .bind(this::applyStrategy)
                .bind(this::saveToDb)
                .bind(this::mapToResponse)
                .get();
    }

    private Result<CreationProductRequest> applyStrategy(CreationProductRequest request) {
        try {
            var category = Category.valueOf(request.getCategory().toUpperCase());
            var strategy = factory.get(category);

            strategy.validate(request);
            strategy.enrich(request);
            log.atInfo().log("inside the applying and finding the strategy");
            return Result.success(request);
        } catch (Exception e) {
            log.atError().log(e.getMessage());
            return Result.failure(e.getMessage());
        }
    }

    private Result<ProductEntity> saveToDb(CreationProductRequest request) {
        try {
            ProductEntity productEntity = productMapper.toEntity(request);
            productRepository.save(productEntity);
            log.atInfo().log("saving into db final step");
            return Result.success(productEntity);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    private Result<ProductResponse> mapToResponse(ProductEntity productEntity) {
        return Result.success(productMapper.toResponse(productEntity));
    }
}
