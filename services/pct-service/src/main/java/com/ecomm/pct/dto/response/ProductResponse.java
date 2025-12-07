package com.ecomm.pct.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String skuCode;
    private Map<String,Object> attributes;
}
