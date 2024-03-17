package com.github.dariakozh.storage.model;

import com.github.dariakozh.storage.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Класс представляет объектное представление класса товара в базе данных.
 * Аннотация @Entity используется для указания того, что данный класс является сущностью.
 */
@Entity
@Table(name = "products")
@Data
@Schema(description = "Сущность товар")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 2, max = 10)
    @Schema(description = "Артикул товара")
    @Column(nullable = false, unique = true)
    private String item;

    @NotBlank
    @Schema(description = "Наименование товара")
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Schema(description = "Описание товара")
    @Column(nullable = false)
    private String description;

    @NotNull
    @Schema(description = "Категория товара")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @PositiveOrZero
    @Schema(description = "Стоимость товара")
    @Column(nullable = false)
    private Double price;

    @PositiveOrZero
    @Schema(description = "Количество товара")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Schema(description = "Дата и время изменения количества товара")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime lastModifiedDate;

    @NotNull
    @Schema(description = "Дата создания товара")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    public static Product of(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setItem(productDto.item());
        product.setTitle(productDto.title());
        product.setDescription(productDto.description());
        product.setCategory(category);
        product.setPrice(productDto.price());
        product.setQuantity(productDto.quantity());
        product.setLastModifiedDate(LocalDateTime.now());
        product.setCreationDate(LocalDate.now());

        return product;
    }
}
