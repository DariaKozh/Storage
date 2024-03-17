package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.exception.NotFoundException;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с категориями товаров.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Метод создания категории.
     *
     * @param categoryDto - входные данные категории товара
     * @return Category
     */
    @Override
    public Category createCategory(CategoryDto categoryDto) {
        return categoryRepository.save(Category.of(categoryDto));
    }

    /**
     * Метод получения категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    @Override
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("Категория с title = " + title + " не найдена"));
    }

    /**
     * Метод получения всех категорий.
     *
     * @return List<Category>
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories().orElseThrow(() -> new NotFoundException("Категории не найдены"));
    }

    /**
     * Метод удаления категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    @Transactional
    @Override
    public Category deleteCategoryByTitle(String title) {
        Category category = categoryRepository.findByTitle(title).orElseThrow(() ->
                new NotFoundException("Категория с title = " + title + " не найдена"));
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
    public Category updateCategory(Category newCategory) {
        Category category = categoryRepository.findById(newCategory.getId()).orElseThrow(() ->
                new NotFoundException("Категория не найдена"));
        category.setTitle(newCategory.getTitle());
        category.setDescription(newCategory.getDescription());
        return categoryRepository.save(category);
    }
}
