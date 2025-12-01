package com.ecomm.pct.service.impl;


import com.ecomm.events.ProducerCreateEvent;
import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.response.ProductResponse;
import com.ecomm.pct.entity.ProductEntity;
import com.ecomm.pct.enums.Category;
import com.ecomm.pct.kafka.ProductEventPublisher;
import com.ecomm.pct.mapper.ProductMapper;
import com.ecomm.pct.repository.ProductRepository;
import com.ecomm.pct.service.ProductService;
import com.ecomm.pct.service.factory.ProductStrategyFactory;
import com.ecomm.railway.Result;
import jakarta.transaction.Transactional;
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
    private final ProductEventPublisher productEventPublisher;

    @Override
    public ProductResponse createProduct(CreationProductRequest request) {
        log.atInfo().log("inside the create product service");
        return Result.success(request)
                .bind(this::applyStrategy)
                .bind(this::validateByUnqiueName)
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

    @Transactional
    private Result<ProductEntity> saveToDb(CreationProductRequest request) {
        try {
            ProductEntity productEntity = productMapper.toEntity(request);
            productRepository.save(productEntity);
            log.atInfo().log("saving into db final step");
            log.atInfo().log("product entity saving :: {}", productEntity);
            ProducerCreateEvent producerCreateEvent = productMapper.toEvent(productEntity);
            productEventPublisher.publishProductCreated(producerCreateEvent);
            return Result.success(productEntity);
        } catch (RuntimeException e) {
            // Re-throw the exception to force Spring's transaction manager to roll back the DB changes.
           throw e;
        }catch (Exception e) {
            log.atError().log("Transaction failed due to checked exception: {}",e.getMessage());
            return Result.failure(e.getMessage());
        }
    }

    private Result<ProductResponse> mapToResponse(ProductEntity productEntity) {
        log.atInfo().log("product entity :: {}", productEntity);
        return Result.success(productMapper.toResponse(productEntity));
    }

    private Result<CreationProductRequest> validateByUnqiueName(CreationProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            String errorMessage = "Product creation failed: A product named '" + productRequest.getName() + "' already exists.";
            return Result.failure(errorMessage);
        }
        return Result.success(productRequest);
    }
}
