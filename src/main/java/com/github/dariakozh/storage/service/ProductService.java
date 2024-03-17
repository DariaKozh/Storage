package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Product;

import java.util.List;

/**
 * Интерфейс ProductService представляет сервис для работы с товарами.
 */
public interface ProductService {
    /**
     * Метод создания товара.
     *
     * @param productDto - входные данные товара
     * @return Product
     */
    Product createProduct(ProductDto productDto);

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
    List<Product> getAllProductsByCategoryTitle(String categoryTitle);

    /**
     * Метод получения товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    Product getProductsByItem(String item);

    /**
     * Метод удаления товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    Product deleteProductByItem(String item);

    /**
     * Метод изменения параметров товара по артикулу.
     *
     * @param newProduct - обновленный товар
     * @return Product
     */
    Product updateProduct(Product newProduct);
}
