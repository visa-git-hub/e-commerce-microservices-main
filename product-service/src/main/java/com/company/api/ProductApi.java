package com.company.api;

import com.company.dto.ProductDTO;
import com.company.dto.request.CreateProductDTORequest;
import com.company.dto.request.UpdateProductDTORequest;
import com.company.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * REST API for product-related operations.
 */
@RestController
@RequestMapping("/v1/product")
public class ProductApi {

    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = Objects.requireNonNull(productService, "ProductService cannot be null");
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTORequest createProductDTORequest) {
        return ResponseEntity.ok(productService.createProduct(createProductDTORequest));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductDTORequest updateProductDTORequest) {
        return ResponseEntity.ok(productService.updateProduct(updateProductDTORequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
