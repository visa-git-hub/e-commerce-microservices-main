package com.company.dto.request;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for creating a product request using Java 21 record.
 */
public record CreateProductDTORequest(
        String name,
        String code,
        String description,
        String color,
        String dimensions,
        BigDecimal price,
        Integer categoryId
) {
    public CreateProductDTORequest {
        Objects.requireNonNull(name, "Product name cannot be null");
        Objects.requireNonNull(categoryId, "Category ID cannot be null");

        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
    }
}
