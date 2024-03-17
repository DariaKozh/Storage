package com.github.dariakozh.storage.repository;


import com.github.dariakozh.storage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью Product - товар.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Метод получения всех товаров.
     *
     * @return List<Product>
     */
    @Query("Select p from Product p")
    Optional<List<Product>> findAllProducts();

    /**
     * Метод получения всех товаров по категории.
     *
     * @param categoryTitle - наименование категории
     * @return List<Product>
     */
    @Query("Select p from Product p where category.title = :categoryTitle")
    Optional<List<Product>> findAllProductsByCategoryTitle(@Param("categoryTitle")String categoryTitle);

    /**
     * Метод получения товара по артикулу.
     *
     * @param item - артикул товара
     * @return Product
     */
    Optional<Product> findByItem(String item);

    /**
     * Метод удаления товара по артикулу.
     *
     * @param item - артикул товара
     */
    void deleteAllByItem(String item);
}
