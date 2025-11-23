package com.ecomm.pct.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {


    @PostMapping
    public String createProduct(){
        return null;
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
