package com.github.dariakozh.storage.controller;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.service.ProductService;
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
public class ProductController {
    private final ProductService productService;

    /**
     * Метод создания товара.
     *
     * @param productDto - входные данные товара
     * @return Product
     */
    @PostMapping("/create")
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
    public List<Product> getAllProductsByCategoryTitle(@RequestParam(name = "category")String categoryTitle) {
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
    public Product getProductsByItem(@RequestParam(name = "item")String item) {
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
    public Product deleteProductByItem(@RequestParam(name = "item") String item) {
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
    public Product updateProduct(@RequestBody Product newProduct) {
        log.debug("[updateProduct]newProduct ={}", newProduct);
        return productService.updateProduct(newProduct);
    }
}
