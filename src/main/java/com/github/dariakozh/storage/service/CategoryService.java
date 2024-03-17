package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.model.Category;

import java.util.List;

/**
 * Интерфейс CategoryService представляет сервис для работы с категориями товаров.
 */
public interface CategoryService {
    /**
     * Метод создания категории.
     *
     * @param categoryDto - входные данные категории товара
     * @return Category
     */
    Category createCategory(CategoryDto categoryDto);

    /**
     * Метод получения категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    Category getCategoryByTitle(String title);

    /**
     * Метод получения всех категорий.
     *
     * @return List<Category>
     */
    List<Category> getAllCategories();

    /**
     * Метод удаления категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    Category deleteCategoryByTitle(String title);

    /**
     * Метод изменения категории.
     *
     * @param newCategory - обновленная категория товара
     * @return Category
     */
    Category updateCategory(Category newCategory);
}
