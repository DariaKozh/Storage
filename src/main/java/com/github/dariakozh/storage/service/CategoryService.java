package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.model.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Интерфейс CategoryService представляет сервис для работы с категориями товаров.
 */
@Validated
public interface CategoryService {
    /**
     * Метод создания категории.
     *
     * @param categoryDto - входные данные категории товара
     * @return Category
     */
    Category createCategory(@Valid CategoryDto categoryDto);

    /**
     * Метод получения категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    Category getCategoryByTitle(@NotBlank String title);

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
    Category deleteCategoryByTitle(@NotBlank String title);

    /**
     * Метод изменения категории.
     *
     * @param newCategory - обновленная категория товара
     * @return Category
     */
    Category updateCategory(@Valid Category newCategory);
}
