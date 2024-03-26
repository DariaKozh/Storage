package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.ProductDto;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.model.Product;
import com.github.dariakozh.storage.repository.CategoryRepository;
import com.github.dariakozh.storage.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    private ProductDto productDto;
    private Product product;
    private Category category;

    @BeforeEach
    void setUp() {
        productDto = new ProductDto("11111", "TestTitle", "TestDesc", "TestCategory", 12.12, 300);
        category = new Category(UUID.randomUUID(), "TestCategory", "TestDesc");
        product = Product.of(productDto, category);
    }

    @DisplayName("Проверка создания товара")
    @Test
    void createProduct() {
        when(productRepository.save(any())).thenReturn(product);
        when(categoryRepository.findByTitle(productDto.categoryTitle())).thenReturn(Optional.of(category));
        Product testProduct = productService.createProduct(productDto);
        assertEquals(product, testProduct);
        verify(categoryRepository, times(1)).findByTitle(any());
        verify(productRepository, times(1)).save(any());
    }

    @DisplayName("Проверка получения всех товаров")
    @Test
    void getAllProducts() {
        when(productRepository.findAllProducts()).thenReturn(Optional.of(List.of(product)));
        assertEquals(List.of(product), productService.getAllProducts());
        verify(productRepository, times(1)).findAllProducts();
    }

    @DisplayName("Проверка поиска товаров по наименованию категории")
    @Test
    void getAllProductsByCategoryTitle() {
        when(productRepository.findAllProductsByCategoryTitle(category.getTitle())).thenReturn(Optional.of(List.of(product)));
        assertEquals(List.of(product), productService.getAllProductsByCategoryTitle(category.getTitle()));
        verify(productRepository, times(1)).findAllProductsByCategoryTitle(anyString());
    }

    @DisplayName("Проверка поиска товара по артикулу")
    @Test
    void getProductsByItem() {
        when(productRepository.findByItem(product.getItem())).thenReturn(Optional.of(product));
        assertEquals(product, productService.getProductsByItem(product.getItem()));
        verify(productRepository, times(1)).findByItem(anyString());
    }

    @DisplayName("Проверка удаления товара по артикулу")
    @Test
    void deleteProductByItem() {
        when(productRepository.findByItem(product.getItem())).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteAllByItem(product.getItem());
        assertEquals(product, productService.deleteProductByItem(product.getItem()));
        verify(productRepository, times(1)).deleteAllByItem(anyString());
        verify(productRepository, times(1)).findByItem(anyString());
    }

    @DisplayName("Проверка обновления товара")
    @Test
    void updateProduct() {
        when(categoryRepository.findAllCategories()).thenReturn(Optional.of(List.of(category)));
        when(productRepository.findByItem(product.getItem())).thenReturn(Optional.of(product));
        ProductDto testProductDto = new ProductDto(product.getItem(), "newTitle", "newDesc", product.getCategory().getTitle(),
                15.22, product.getQuantity());
        Product testProduct = new Product(product.getId(), product.getItem(), "newTitle", "newDesc", product.getCategory(),
                15.22, product.getQuantity(), product.getLastModifiedDate(), product.getCreationDate());
        when(productRepository.save(testProduct)).thenReturn(testProduct);
        when(categoryRepository.findByTitle(productDto.categoryTitle())).thenReturn(Optional.of(category));
        assertEquals(testProduct, productService.updateProduct(testProductDto));
        verify(productRepository, times(1)).save(any());
        verify(productRepository, times(1)).findByItem(anyString());
        verify(categoryRepository, times(1)).findAllCategories();
    }
}