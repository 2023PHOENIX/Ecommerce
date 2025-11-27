package com.ecomm.pct.controller;

import com.ecomm.pct.dto.request.CreationProductRequest;
import com.ecomm.pct.dto.response.ProductResponse;
import com.ecomm.pct.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(@RequestBody @Valid CreationProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PutMapping
    public String updateProduct(){
        return null;
    }

    @DeleteMapping
    public String deleteProduct(){
        return null;
    }

}
