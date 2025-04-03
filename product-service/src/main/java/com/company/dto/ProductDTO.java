package com.company.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Product Data Transfer Object (DTO) using Java 21 features.
 */
public record ProductDTO(
        Long id,
        String name,
        String code,
        String description,
        String color,
        String dimensions,
        BigDecimal price,
        CategoryDTO category,
        List<ProductImageDTO> productImages
) {
    // Compact constructor with validation
    public ProductDTO {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Product name must have at least 3 characters.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }

        // Ensure immutable list to prevent accidental modifications
        productImages = productImages != null ? List.copyOf(productImages) : List.of();
    }
}
