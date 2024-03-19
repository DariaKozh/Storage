package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.exception.NotFoundException;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с товарами.
 */
@Service
@RequiredArgsConstructor
@Validated
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
    public Product createProduct(@Valid ProductDto productDto) {
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
        List<Product> products = productRepository.findAllProducts().orElseThrow(() -> new NotFoundException("Товары не найдены"));
        if (products.isEmpty())
            throw new NotFoundException("Товары не найдены");
        return products;
    }

    /**
     * Метод получения всех товаров по категории.
     *
     * @param categoryTitle - наименование категории
     * @return List<Product>
     */
    @Override
    public List<Product> getAllProductsByCategoryTitle(@NotBlank String categoryTitle) {
        List<Product> products= productRepository.findAllProductsByCategoryTitle(categoryTitle).orElseThrow(() -> new NotFoundException("Товары не найдены"));
        if (products.isEmpty())
            throw new NotFoundException("Товары не найдены");
        return products;
    }

    /**
     * Метод получения товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    @Override
    public Product getProductsByItem(@Size(min = 2, max = 10) String item) {
        return productRepository.findByItem(item).orElseThrow(() -> new NotFoundException("Товар с артикулом " + item + " не найден"));
    }

    /**
     * Метод удаления товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    @Transactional
    @Override
    public Product deleteProductByItem(@Size(min = 2, max = 10) String item) {
        Product product = productRepository.findByItem(item).orElseThrow(() ->
                new NotFoundException("Товар с артикулом " + item + " не найден"));
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
    public Product updateProduct(@Valid Product newProduct) {
        if (!categoryService.getAllCategories().contains(newProduct.getCategory()))
            throw new NotFoundException("Категория с наименованием " + newProduct.getCategory().getTitle() + " не найдена");

        Product product = productRepository.findByItem(newProduct.getItem()).orElseThrow(() ->
                new NotFoundException("Товар с артикулом = " + newProduct.getItem() + " не найден"));
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
