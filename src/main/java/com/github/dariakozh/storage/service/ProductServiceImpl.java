package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.exception.NotFoundException;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.repository.CategoryRepository;
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
import java.util.Optional;

/**
 * Сервис для работы с товарами.
 */
@Service
@RequiredArgsConstructor
@Validated
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    /**
     * Метод создания товара.
     *
     * @param productDto - входные данные товара
     * @return Product
     */
    @Override
    public Product createProduct(@Valid ProductDto productDto) {
        Category category = categoryRepository.findByTitle(productDto.categoryTitle())
                .orElseThrow(() -> new NotFoundException("Категория с наименованием " + productDto.categoryTitle() + " не найдена"));
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
     * @param productDto - обновленный товар
     * @return Product
     */
    @Transactional
    @Override
    public Product updateProduct(@Valid ProductDto productDto) {
        Optional<List<Category>> categories = categoryRepository.findAllCategories();
        if (categories.isPresent()) {
            if (!categories.get().stream().map(Category::getTitle).toList().contains(productDto.categoryTitle())) {
                throw new NotFoundException("Категория с наименованием " + productDto.categoryTitle() + " не найдена");
            }
        } else {
            throw new NotFoundException("Категории не найдены");
        }

        Product product = productRepository.findByItem(productDto.item()).orElseThrow(() ->
                new NotFoundException("Товар с артикулом = " + productDto.item() + " не найден"));
        product.setTitle(productDto.title());
        product.setDescription(productDto.description());
        product.setCategory(categoryRepository.findByTitle(productDto.categoryTitle()).get());
        product.setPrice(productDto.price());
        if (!product.getQuantity().equals(productDto.quantity())){
            product.setQuantity(productDto.quantity());
            product.setLastModifiedDate(LocalDateTime.now());
        }

        return productRepository.save(product);
    }
}
