package com.github.dariakozh.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * Класс dto для товара.
 *
 * @param item - артикул
 * @param title - наименование
 * @param description - описание
 * @param categoryTitle - наименование категории
 * @param price - стоимость
 * @param quantity - количество
 */
@Schema(description = "Сущность товар")
public record ProductDto(@Schema(description = "Артикул товара") @Size(min = 2, max = 10) String item,
                         @Schema(description = "Наименование товара")  @NotBlank String title,
                         @Schema(description = "Описание товара")  @NotBlank String description,
                         @Schema(description = "Наименование категории товара")  @NotBlank String categoryTitle,
                         @Schema(description = "Стоимость товара") @PositiveOrZero Double price,
                         @Schema(description = "Количество товара") @PositiveOrZero Integer quantity) {
}
