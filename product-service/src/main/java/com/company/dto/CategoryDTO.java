package com.company.dto;

/**
 * CategoryDTO using Java 21 Records
 */
public record CategoryDTO(Integer id, String name) {
    public CategoryDTO {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Category name must be at least 3 characters long");
        }
    }
}
