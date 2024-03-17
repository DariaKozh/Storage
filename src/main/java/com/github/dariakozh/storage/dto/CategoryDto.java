package com.github.dariakozh.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Класс dto для категории.
 *
 * @param title - наименование
 * @param description - описание
 */
@Schema(description = "Сущность категория товара")
public record CategoryDto(@Schema(description = "Наименование категории товара")  @NotBlank String title,
                          @Schema(description = "Описание категория товара")  @NotBlank String description) {
}
