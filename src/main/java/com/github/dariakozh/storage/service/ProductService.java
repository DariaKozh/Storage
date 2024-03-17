package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Интерфейс ProductService представляет сервис для работы с товарами.
 */
@Validated
public interface ProductService {
    /**
     * Метод создания товара.
     *
     * @param productDto - входные данные товара
     * @return Product
     */
    Product createProduct(@Valid ProductDto productDto);

    /**
     * Метод получения всех товаров.
     *
     * @return List<Product>
     */
    List<Product> getAllProducts();

    /**
     * Метод получения всех товаров по категории.
     *
     * @param categoryTitle - наименование категории
     * @return List<Product>
     */
    List<Product> getAllProductsByCategoryTitle(@NotBlank String categoryTitle);

    /**
     * Метод получения товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    Product getProductsByItem(@Size(min = 2, max = 10) String item);

    /**
     * Метод удаления товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    Product deleteProductByItem(@Size(min = 2, max = 10) String item);

    /**
     * Метод изменения параметров товара по артикулу.
     *
     * @param newProduct - обновленный товар
     * @return Product
     */
    Product updateProduct(@Valid Product newProduct);
}
