package com.ecomm.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * new product created event.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerCreateEvent {
    private String productId;
    private String name;
    private String skuCode;
    private BigDecimal price;
    private String category;
    private Instant instant = Instant.now();
}
