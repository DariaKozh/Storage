package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);

    Category getCategoryByTitle(String title);

    List<Category> getAllCategories();

    Category deleteCategoryByTitle(String title);

    Category updateCategory(Category newCategory);
}
