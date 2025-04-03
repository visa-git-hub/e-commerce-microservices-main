package com.company.dto.converter;

import com.company.dto.ProductDTO;
import com.company.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Converts Product entities to ProductDTOs using Java 21 features.
 */
@Component
public record ProductDTOConverter(
        ProductImageDTOConverter productImageDTOConverter,
        CategoryDTOConverter categoryDTOConverter
) {

    public ProductDTO convert(Product from) {
        return new ProductDTO(
                from.id(),
                from.name(),
                from.code(),
                from.color(),
                from.description(),
                from.dimensions(),
                from.price(),
                Optional.ofNullable(from.category()).map(categoryDTOConverter::convert).orElse(null),
                Optional.ofNullable(from.productImages()).map(productImageDTOConverter::convert).orElse(List.of())
        );
    }

    public List<ProductDTO> convert(List<Product> fromList) {
        return fromList.stream()
                .map(this::convert)
                .toList(); // Java 16+ optimized immutable list
    }
}
