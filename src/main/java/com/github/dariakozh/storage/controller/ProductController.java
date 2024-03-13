package com.github.dariakozh.storage.controller;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Метод оздания товара.
     *
     * @param productDto
     * @return Product
     */
    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductDto productDto) {
        log.debug("[createProduct] productInput={}", productDto);
        return productService.createProduct(productDto);
    }

//    /**
//     * Метод получения категории по названию.
//     *
//     * @param title
//     * @return Category
//     */
//    @GetMapping("/getCategory")
//    public Category getCategoryByTitle(@RequestParam(name = "title") String title) {
//        log.debug("[getCategoryByTitle] categoryTitle={}", title);
//        return categoryService.getCategoryByTitle(title);
//    }
//
//    /**
//     * Метод получения всех категорий.
//     *
//     * @return List<Category>
//     */
//    @GetMapping("/all")
//    public List<Category> getAllCategories() {
//        log.debug("[getAllCategories]");
//        return categoryService.getAllCategories();
//    }
//
//    /**
//     * Метод удаления категории по названию.
//     *
//     * @param title
//     * @return Category
//     */
//    @DeleteMapping("/delete")
//    public Category deleteCategoryByTitle(@RequestParam(name = "title") String title) {
//        log.debug("[deleteCategoryByTitle] categoryTitle={}", title);
//        return categoryService.deleteCategoryByTitle(title);
//    }
//
//    /**
//     * Метод изменения категории по названию.
//     *
//     * @param newCategory
//     * @return Category
//     */
//    @PutMapping("/update")
//    public Category updateCategory(@RequestBody Category newCategory) {
//        log.debug("[updateCategory]newCategory={}", newCategory);
//        return categoryService.updateCategory(newCategory);
//    }
}
