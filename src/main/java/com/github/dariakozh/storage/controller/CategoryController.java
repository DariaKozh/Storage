package com.github.dariakozh.storage.controller;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления категориями товаров.
 */
@RestController
@Slf4j
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name="Контроллер категории товара", description="Управляет категориями товара")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Метод создания категории.
     *
     * @param categoryDto - входные данные категории товара
     * @return Category
     */
    @PostMapping("/create")
    @Operation(
            summary = "Создание категории товара",
            description = "Позволяет создать новую категорию"
    )
    public Category createCategory(@RequestBody CategoryDto categoryDto) {
        log.debug("[createCategory] categoryInput={}", categoryDto);
        return categoryService.createCategory(categoryDto);
    }

    /**
     * Метод получения категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    @GetMapping("/getCategory")
    @Operation(
            summary = "Получение категорий товаров",
            description = "Позволяет получить все категории товаров по наименованию"
    )
    public Category getCategoryByTitle(@RequestParam(name = "title") @Parameter(description = "Наименование категории") String title) {
        log.debug("[getCategoryByTitle] categoryTitle={}", title);
        return categoryService.getCategoryByTitle(title);
    }

    /**
     * Метод получения всех категорий.
     *
     * @return List<Category>
     */
    @GetMapping("/all")
    @Operation(
            summary = "Получение категорий товаров",
            description = "Позволяет получить все категории товаров"
    )
    public List<Category> getAllCategories() {
        log.debug("[getAllCategories]");
        return categoryService.getAllCategories();
    }

    /**
     * Метод удаления категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    @DeleteMapping("/delete")
    @Operation(
            summary = "Удаление категории товаров",
            description = "Позволяет удалить категорию товара по наименованию"
    )
    public Category deleteCategoryByTitle(@RequestParam(name = "title") @Parameter(description = "Наименование категории") String title) {
        log.debug("[deleteCategoryByTitle] categoryTitle={}", title);
        return categoryService.deleteCategoryByTitle(title);
    }

    /**
     * Метод изменения категории.
     *
     * @param newCategory - обновленная категория товара
     * @return Category
     */
    @PutMapping("/update")
    @Operation(
            summary = "Изменение категории товаров",
            description = "Позволяет изменить категорию товаров"
    )
    public Category updateCategory(@RequestBody Category newCategory) {
        log.debug("[updateCategory]newCategory={}", newCategory);
        return categoryService.updateCategory(newCategory);
    }
}
