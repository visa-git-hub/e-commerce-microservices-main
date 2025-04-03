package com.company.dto.request;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for updating a product.
 */
public record UpdateProductDTORequest(
        Long id,
        String name,
        String code,
        String description,
        String color,
        String dimensions,
        BigDecimal price,
        Integer categoryId
) {
    public UpdateProductDTORequest {
        Objects.requireNonNull(id, "Product ID cannot be null");
        Objects.requireNonNull(categoryId, "Category ID cannot be null");
        if (name != null && name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
    }
}
