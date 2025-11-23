package com.ecomm.pct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {

    @GetMapping("{categoryId}")
    public String getCategory(@PathVariable String categoryId){
        return null;
    }

    @GetMapping
    public String getAllCategory(){
        return null;
    }
}
