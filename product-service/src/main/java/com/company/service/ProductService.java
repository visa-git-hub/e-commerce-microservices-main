package com.company.service;

import com.company.dto.ProductDTO;
import com.company.dto.converter.ProductDTOConverter;
import com.company.dto.request.CreateProductDTORequest;
import com.company.dto.request.UpdateProductDTORequest;
import com.company.exception.ProductNotFoundException;
import com.company.model.Category;
import com.company.model.Product;
import com.company.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOConverter productDTOConverter;

    public ProductService(ProductRepository productRepository, ProductDTOConverter productDTOConverter) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.productDTOConverter = Objects.requireNonNull(productDTOConverter);
    }

    public ProductDTO createProduct(CreateProductDTORequest request) {
        var category = (request.categoryId() != null) ? new Category(request.categoryId()) : null;
        var product = new Product(
                null,
                request.name(),
                request.code(),
                request.color(),
                request.description(),
                request.dimensions(),
                request.price(),
                true,
                category,
                List.of()
        );

        return productDTOConverter.convert(productRepository.save(product));
    }

    public ProductDTO updateProduct(UpdateProductDTORequest request) {
        var existingProduct = findProductById(request.id());
        var category = new Category(request.categoryId());

        var updatedProduct = new Product(
                existingProduct.id(),
                request.name(),
                request.code(),
                request.color(),
                request.description(),
                request.dimensions(),
                request.price(),
                existingProduct.isActive(),
                category,
                existingProduct.productImages()
        );

        return productDTOConverter.convert(productRepository.save(updatedProduct));
    }

    public List<ProductDTO> getAllProduct() {
        return productDTOConverter.convert(productRepository.findAll());
    }

    public void deleteProduct(Long id) {
        productRepository.delete(findProductById(id));
    }

    protected Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }
}
