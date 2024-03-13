package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    @Override
    public Product createProduct(ProductDto productDto) {
        Category category = categoryService.getCategoryByTitle(productDto.categoryTitle());
        return productRepository.save(Product.of(productDto, category));
    }


//    @Override
//    public Category createCategory(CategoryDto categoryDto) {
//        return categoryRepository.save(Category.of(categoryDto));
//    }
//
//    @Override
//    public Category getCategoryByTitle(String title) {
//        return categoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("Категория с title = " + title + " не найдена"));
//    }
//
//    @Override
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAllCategories().orElseThrow(() -> new NotFoundException("Категории не найдены"));
//    }
//
//    @Transactional
//    @Override
//    public Category deleteCategoryByTitle(String title) {
//        Category category = categoryRepository.findByTitle(title).orElseThrow(() ->
//                new NotFoundException("Категория с title = " + title + " не найдена"));
//        categoryRepository.deleteAllByTitle(title);
//        return category;
//    }
//
//    @Override
//    public Category updateCategory(Category newCategory) {
//        Category category = categoryRepository.findById(newCategory.getId()).orElseThrow(() ->
//                new NotFoundException("Категория не найдена"));
//        category.setTitle(newCategory.getTitle());
//        category.setDescription(newCategory.getDescription());
//        return categoryRepository.save(category);
//    }
}
