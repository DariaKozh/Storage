package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.exception.NotFoundException;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с товарами.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    /**
     * Метод создания товара.
     *
     * @param productDto - входные данные товара
     * @return Product
     */
    @Override
    public Product createProduct(ProductDto productDto) {
        Category category = categoryService.getCategoryByTitle(productDto.categoryTitle());
        return productRepository.save(Product.of(productDto, category));
    }

    /**
     * Метод получения всех товаров.
     *
     * @return List<Product>
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts().orElseThrow(() -> new NotFoundException("Товары не найдены"));
    }

    /**
     * Метод получения всех товаров по категории.
     *
     * @param categoryTitle - наименование категории
     * @return List<Product>
     */
    @Override
    public List<Product> getAllProductsByCategoryTitle(String categoryTitle) {
        return productRepository.findAllProductsByCategoryTitle(categoryTitle).orElseThrow(() -> new NotFoundException("Товары не найдены"));
    }

    /**
     * Метод получения товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    @Override
    public Product getProductsByItem(String item) {
        return productRepository.findByItem(item).orElseThrow(() -> new NotFoundException("Товар с item = " + item + " не найден"));
    }

    /**
     * Метод удаления товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    @Transactional
    @Override
    public Product deleteProductByItem(String item) {
        Product product = productRepository.findByItem(item).orElseThrow(() ->
                new NotFoundException("Товар с item = " + item + " не найдена"));
        productRepository.deleteAllByItem(item);
        return product;
    }

    /**
     * Метод изменения параметров товара по артикулу.
     *
     * @param newProduct - обновленный товар
     * @return Product
     */
    @Transactional
    @Override
    public Product updateProduct(Product newProduct) {
        Product product = productRepository.findByItem(newProduct.getItem()).orElseThrow(() ->
                new NotFoundException("Товар с item = " + newProduct.getItem() + " не найдена"));
        product.setTitle(newProduct.getTitle());
        product.setDescription(newProduct.getDescription());
        product.setCategory(newProduct.getCategory());
        product.setPrice(newProduct.getPrice());
        if (!product.getQuantity().equals(newProduct.getQuantity())){
            product.setQuantity(newProduct.getQuantity());
            product.setLastModifiedDate(LocalDateTime.now());
        }

        return productRepository.save(product);
    }
}
