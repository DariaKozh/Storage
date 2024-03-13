package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Product;


public interface ProductService {
    Product createProduct(ProductDto productDto);

//    Category createCategory(CategoryDto categoryDto);
//
//    Category getCategoryByTitle(String title);
//
//    List<Category> getAllCategories();
//
//    Category deleteCategoryByTitle(String title);
//
//    Category updateCategory(Category newCategory);
}
