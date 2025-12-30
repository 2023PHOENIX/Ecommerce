package com.ecomm.pct.controller;

import com.ecomm.dto.exception.ApiResponseDTO;
import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    final ProductService productService;

    @GetMapping
    public ApiResponseDTO<Object> getAllProducts(@RequestParam(value = "page") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize) {
        log.atInfo().log("Fetching all products - Page: {}, Page Size: {}", pageNumber, pageSize);
        return ApiResponseDTO.builder().status(HttpStatus.OK.value()).data(productService.getAllProduct(pageNumber, pageSize)).build();
    }

    @PostMapping
    public ApiResponseDTO<Object> createProduct(@RequestBody @Valid CreationProductRequest productRequest) {
        return ApiResponseDTO.builder().status(HttpStatus.CREATED.value()).data(productService.createProduct(productRequest)).build();
    }

    @PutMapping
    public String updateProduct() {
        return null;
    }

    @DeleteMapping
    public String deleteProduct() {
        return null;
    }

}
