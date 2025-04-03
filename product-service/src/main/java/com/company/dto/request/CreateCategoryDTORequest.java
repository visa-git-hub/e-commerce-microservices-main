package com.company.dto.request;

import java.util.Objects;

/**
 * Immutable CreateCategoryDTORequest using Java 21 features.
 */
public record CreateCategoryDTORequest(Integer id, String name) {

    public CreateCategoryDTORequest {
        Objects.requireNonNull(name, "Category name cannot be null");

        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long.");
        }
    }
}
