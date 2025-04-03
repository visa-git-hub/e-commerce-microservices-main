package com.company.dto;

/**
 * ProductImageDTO using Java 21 record feature
 */
public record ProductImageDTO(Long id, String imageUrl, Long productId) {
}
