package com.github.dariakozh.storage.model;

import com.github.dariakozh.storage.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public static Category of(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setTitle(categoryDto.title());
        category.setDescription(categoryDto.description());

        return category;
    }
}
