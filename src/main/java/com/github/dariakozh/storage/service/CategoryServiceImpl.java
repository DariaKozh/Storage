package com.github.dariakozh.storage.service;

import com.github.dariakozh.storage.dto.CategoryDto;
import com.github.dariakozh.storage.exception.NotFoundException;
import com.github.dariakozh.storage.model.Category;
import com.github.dariakozh.storage.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        return categoryRepository.save(Category.of(categoryDto));
    }

    @Override
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title).orElseThrow(() -> new NotFoundException("Категория с title = " + title + " не найдена"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories().orElseThrow(() -> new NotFoundException("Категории не найдены"));
    }

    @Transactional
    @Override
    public Category deleteCategoryByTitle(String title) {
        Category category = categoryRepository.findByTitle(title).orElseThrow(() ->
                new NotFoundException("Категория с title = " + title + " не найдена"));
        categoryRepository.deleteAllByTitle(title);
        return category;
    }

    @Override
    public Category updateCategory(Category newCategory) {
        Category category = categoryRepository.findById(newCategory.getId()).orElseThrow(() ->
                new NotFoundException("Категория не найдена"));
        category.setTitle(newCategory.getTitle());
        category.setDescription(newCategory.getDescription());
        return categoryRepository.save(category);
    }
}
