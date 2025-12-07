package com.ecomm.pct.entity;


import com.ecomm.pct.enums.Category;
import com.ecomm.pct.util.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "SKU cannot be blank")
    @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "SKU must be alphanumeric with hyphens")
    @Column(nullable = false, length = 100)
    private String skuCode;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Convert(converter = JsonConverter.class)
    private Map<String, Object> attributes;

    private boolean delete = false;
}
