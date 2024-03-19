package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.exception.NotFoundException;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.repository.CategoryRepository;
import com.github.dariakozh.storage.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Сервис для работы с категориями товаров.
 */
@Service
@RequiredArgsConstructor
@Validated
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    /**
     * Метод создания категории.
     *
     * @param categoryDto - входные данные категории товара
     * @return Category
     */
    @Override
    public Category createCategory(@Valid CategoryDto categoryDto) {
        return categoryRepository.save(Category.of(categoryDto));
    }

    /**
     * Метод получения категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    @Override
    public Category getCategoryByTitle(@NotBlank String title) {
        return categoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("Категория с наименованием " + title + " не найдена"));
    }

    /**
     * Метод получения всех категорий.
     *
     * @return List<Category>
     */
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAllCategories().orElseThrow(() -> new NotFoundException("Категории не найдены"));
        if (categories.isEmpty())
            throw new NotFoundException("Категории не найдены");
        return categories;
    }

    /**
     * Метод удаления категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    @Transactional
    @Override
    public Category deleteCategoryByTitle(@NotBlank String title) {
        Category category = categoryRepository.findByTitle(title).orElseThrow(() ->
                new NotFoundException("Категория с наименованием " + title + " не найдена"));
        if(!productRepository.findAllProductsByCategoryTitle(title).get().isEmpty()) {
            throw new IllegalArgumentException("В категории с наименованием " + title + " есть товары");
        }
        categoryRepository.deleteAllByTitle(title);
        return category;
    }

    /**
     * Метод изменения категории.
     *
     * @param newCategory - обновленная категория товара
     * @return Category
     */
    @Transactional
    @Override
    public Category updateCategory(@Valid Category newCategory) {
        Category category = categoryRepository.findById(newCategory.getId()).orElseThrow(() ->
                new NotFoundException("Категория не найдена"));
        category.setTitle(newCategory.getTitle());
        category.setDescription(newCategory.getDescription());
        return categoryRepository.save(category);
    }
}
