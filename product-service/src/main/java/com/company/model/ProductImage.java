package com.company.model;

import java.io.Serializable;

/**
 * Represents an image associated with a product.
 *
 * @param id        The unique identifier of the image.
 * @param imageUrl  The URL of the image.
 * @param product   The associated product.
 */
public record ProductImage(Long id, String imageUrl, Product product) implements Serializable {

        private static final long serialVersionUID = 1L;

        // Compact constructor for validation
    public ProductImage {
                if (imageUrl == null || imageUrl.isBlank()) {
                        throw new IllegalArgumentException("Image URL cannot be null or blank");
                }
        }

        // Convenience factory methods for different initialization needs
        public static ProductImage of(String imageUrl, Product product) {
                return new ProductImage(null, imageUrl, product);
        }

        public static ProductImage withId(Long id, String imageUrl, Product product) {
                return new ProductImage(id, imageUrl, product);
        }

        // Utility method to create a copy with a new ID
        public ProductImage withId(Long newId) {
                return new ProductImage(newId, this.imageUrl, this.product);
        }
}
