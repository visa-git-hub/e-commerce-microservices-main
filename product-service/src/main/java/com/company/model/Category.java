package com.company.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Category model using Java 21 features
 */
public record Category(
        Integer id,
        String name,
        List<Product> productList
) implements Serializable {

    private static final long serialVersionUID = 1L;

    // Primary constructor (Java automatically provides this)
    public Category {
        Objects.requireNonNull(name, "Category name cannot be null");
    }

    // Secondary constructor to allow instantiation with just id
    public Category(Integer id) {
        this(id, "Unknown", List.of()); // Provide default values
    }
}

