package com.github.dariakozh.storage.dto;

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
public record ProductDto(String item, String title, String description, String categoryTitle, Double price, Integer quantity) {
}
