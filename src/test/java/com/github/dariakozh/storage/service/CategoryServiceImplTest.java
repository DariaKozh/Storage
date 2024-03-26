package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    private CategoryDto categoryDto;
    private Category category;
    private Category category2;

    @BeforeEach
    void setUp() {
        categoryDto = new CategoryDto("TestTitle", "TestDesc");
        category = new Category(UUID.randomUUID(), "TestTitle", "TestDesc");
        category2 = new Category(UUID.randomUUID(), "TestTitle2", "TestDesc2");
    }

    @DisplayName("Проверка создания категории")
    @Test
    void createCategory() {
        when(categoryRepository.save(any())).thenReturn(category);
        Category testCategory = categoryService.createCategory(categoryDto);
        assertEquals(category, testCategory);
        verify(categoryRepository, times(1)).save(any());
    }

    @DisplayName("Проверка поиска категории по наименованию")
    @Test
    void getCategoryByTitle() {
        when(categoryRepository.findByTitle(category.getTitle())).thenReturn(Optional.of(category));
        Category testCategory = categoryService.getCategoryByTitle(category.getTitle());
        assertEquals(category, testCategory);
        verify(categoryRepository, times(1)).findByTitle(anyString());
    }

    @DisplayName("Проверка получения всех категорий")
    @Test
    void getAllCategories() {
        when(categoryRepository.findAllCategories()).thenReturn(Optional.of(List.of(category, category2)));
        assertEquals(List.of(category, category2), categoryService.getAllCategories());
        verify(categoryRepository, times(1)).findAllCategories();
    }

    @DisplayName("Проверка удаления категории по наименованию")
    @Test
    void deleteCategoryByTitle() {
        doNothing().when(categoryRepository).deleteAllByTitle(category.getTitle());
        when(categoryRepository.findByTitle(category.getTitle())).thenReturn(Optional.of(category));
        when(productRepository.findAllProductsByCategoryTitle(category.getTitle())).thenReturn(Optional.of(new ArrayList<Product>()));
        assertEquals(category, categoryService.deleteCategoryByTitle(category.getTitle()));
        verify(categoryRepository, times(1)).deleteAllByTitle(anyString());
        verify(categoryRepository, times(1)).findByTitle(anyString());
    }

    @DisplayName("Проверка обновления категории")
    @Test
    void updateCategory() {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        Category testCategory = new Category(category.getId(), "newTitle", "newDesc");
        when(categoryRepository.save(testCategory)).thenReturn(testCategory);
        assertEquals(testCategory, categoryService.updateCategory(testCategory));
        verify(categoryRepository, times(1)).save(any());
    }
}