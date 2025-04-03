package com.company.service;

import com.company.dto.CategoryDTO;
import com.company.dto.converter.CategoryDTOConverter;
import com.company.dto.request.CreateCategoryDTORequest;
import com.company.model.Category;
import com.company.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public  class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOConverter categoryDTOConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryDTOConverter categoryDTOConverter) {
        this.categoryRepository = Objects.requireNonNull(categoryRepository, "CategoryRepository cannot be null");
        this.categoryDTOConverter = Objects.requireNonNull(categoryDTOConverter, "CategoryDTOConverter cannot be null");
    }

    public CategoryDTO createCategory(CreateCategoryDTORequest createCategoryDTORequest) {
        var category = new Category(null, createCategoryDTORequest.name(), List.of());
        return categoryDTOConverter.convert(categoryRepository.save(category));
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryDTOConverter::convert)
                .toList();
    }

    public CategoryDTO findByName(String name) {
        var category = categoryRepository.findByName(name);
        if (category == null) {
            throw new IllegalArgumentException("Category not found: " + name);
        }
        return categoryDTOConverter.convert(category);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
