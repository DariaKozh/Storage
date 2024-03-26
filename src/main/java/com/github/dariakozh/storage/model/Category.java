package com.github.dariakozh.storage.model;

import com.github.dariakozh.storage.dto.CategoryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Класс представляет объектное представление класса категория товара в базе данных.
 * Аннотация @Entity используется для указания того, что данный класс является сущностью.
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность категория товара")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @NotBlank
    @Schema(description = "Наименование категории товара")
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank
    @Schema(description = "Описание категория товара")
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
