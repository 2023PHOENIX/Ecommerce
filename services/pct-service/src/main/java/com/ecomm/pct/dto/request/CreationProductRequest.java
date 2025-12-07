package com.ecomm.pct.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CreationProductRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String skuCode;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotBlank
    private String category;
    private Map<String,Object> attributes;
}
