package com.github.dariakozh.storage.controller;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления товарами.
 */
@RestController
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name="Контроллер товара", description="Управляет товарами")
public class ProductController {
    private final ProductService productService;

    /**
     * Метод создания товара.
     *
     * @param productDto - входные данные товара
     * @return Product
     */
    @PostMapping("/create")
    @Operation(
            summary = "Создание товара",
            description = "Позволяет создать новый товар"
    )
    public Product createProduct(@RequestBody ProductDto productDto) {
        log.debug("[createProduct] productInput={}", productDto);
        return productService.createProduct(productDto);
    }

    /**
     * Метод получения всех товаров.
     *
     * @return List<Product>
     */
    @GetMapping("/all")
    @Operation(
            summary = "Получение товаров",
            description = "Позволяет получить все товары"
    )
    public List<Product> getAllProducts() {
        log.debug("[getAllProducts]");
        return productService.getAllProducts();
    }

    /**
     * Метод получения всех товаров по категории.
     *
     * @param categoryTitle - наименование категории
     * @return List<Product>
     */
    @GetMapping
    @Operation(
            summary = "Получение товаров",
            description = "Позволяет получить товары по наименованию категории товара"
    )
    public List<Product> getAllProductsByCategoryTitle(@RequestParam(name = "category") @Parameter(description = "Наименование категории")
                                                           String categoryTitle) {
        log.debug("[getAllProductsByCategoryTitle] categoryTitle={}", categoryTitle);
        return productService.getAllProductsByCategoryTitle(categoryTitle);
    }

    /**
     * Метод получения товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    @GetMapping("/find")
    @Operation(
            summary = "Получение товаров",
            description = "Позволяет получить товары по артикулу"
    )
    public Product getProductsByItem(@RequestParam(name = "item") @Parameter(description = "Артикул товара") String item) {
        log.debug("[getProductsByItem] item={}", item);
        return productService.getProductsByItem(item);
    }

    /**
     * Метод удаления товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    @DeleteMapping("/delete")
    @Operation(
            summary = "Удаление товаров",
            description = "Позволяет удалить товары по артикулу"
    )
    public Product deleteProductByItem(@RequestParam(name = "item") @Parameter(description = "Артикул товара") String item) {
        log.debug("[ deleteProductByItem] item={}", item);
        return productService.deleteProductByItem(item);
    }

    /**
     * Метод изменения параметров товара по артикулу.
     *
     * @param newProduct - обновленный товар
     * @return Product
     */
    @PutMapping("/update")
    @Operation(
            summary = "Изменение товаров",
            description = "Позволяет изменить товар"
    )
    public Product updateProduct(@RequestBody Product newProduct) {
        log.debug("[updateProduct]newProduct ={}", newProduct);
        return productService.updateProduct(newProduct);
    }
}
