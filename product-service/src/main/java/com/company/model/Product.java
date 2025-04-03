package com.company.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Product model using Java 21 features
 */
public record Product(
        Long id,
        String code,
        String color,
        String description,
        String dimensions,
        String name,
        BigDecimal price,
        Boolean isActive,
        Category category,
        List<ProductImage> productImages
) implements Serializable {

    private static final long serialVersionUID = 1L;

    public Product {
        Objects.requireNonNull(name, "Product name cannot be null");
        Objects.requireNonNull(code, "Product code cannot be null");
        Objects.requireNonNull(price, "Product price cannot be null");
    }
}