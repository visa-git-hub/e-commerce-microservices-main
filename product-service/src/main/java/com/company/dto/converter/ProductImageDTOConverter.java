package com.company.dto.converter;

import com.company.dto.ProductImageDTO;
import com.company.model.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Converts ProductImage entities to ProductImageDTOs using Java 21 features.
 */
@Component
public record ProductImageDTOConverter() {

    public ProductImageDTO convert(ProductImage from) {
        return new ProductImageDTO(
                from.id(),
                from.imageUrl(),
                Optional.ofNullable(from.product()).map(p -> p.id()).orElse(null)
        );
    }

    public List<ProductImageDTO> convert(List<ProductImage> fromList) {
        return fromList.stream()
                .map(this::convert)
                .toList();  // Java 16+ optimized immutable list
    }
}
