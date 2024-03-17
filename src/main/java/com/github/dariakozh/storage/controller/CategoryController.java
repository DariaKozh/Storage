package com.github.dariakozh.storage.controller;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.service.CategoryService;
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
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Метод создания категории.
     *
     * @param categoryDto - входные данные категории товара
     * @return Category
     */
    @PostMapping("/create")
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
    public Category getCategoryByTitle(@RequestParam(name = "title") String title) {
        log.debug("[getCategoryByTitle] categoryTitle={}", title);
        return categoryService.getCategoryByTitle(title);
    }

    /**
     * Метод получения всех категорий.
     *
     * @return List<Category>
     */
    @GetMapping("/all")
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
    public Category deleteCategoryByTitle(@RequestParam(name = "title") String title) {
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
    public Category updateCategory(@RequestBody Category newCategory) {
        log.debug("[updateCategory]newCategory={}", newCategory);
        return categoryService.updateCategory(newCategory);
    }
}
