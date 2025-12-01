package com.ecomm.events;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * new product created event.
 */
@Data
public class ProducerCreateEvent {
    private String productId;
    private String name;
    private BigDecimal price;
    private String category;
    private Instant instant = Instant.now();
}
