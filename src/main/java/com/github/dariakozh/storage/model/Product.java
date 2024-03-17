package com.github.dariakozh.storage.model;

import com.github.dariakozh.storage.dto.ProductDto;
import jakarta.persistence.*;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String item;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime lastModifiedDate;

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
