package com.company.api;

import com.company.dto.CategoryDTO;
import com.company.dto.request.CreateCategoryDTORequest;
import com.company.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/category")
public class CategoryApi {

    private final CategoryService categoryService;

    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addcategory")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryDTORequest request) {
        var category = categoryService.createCategory(request);
        return ResponseEntity.ok(category);
    }
}
