package com.github.dariakozh.storage.repository;

import com.github.dariakozh.storage.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью Category - категория товара.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    /**
     * Метод получения категории по наименованию.
     *
     * @param title - наименование категории
     * @return Category
     */
    Optional<Category> findByTitle(String title);

    /**
     * Метод получения всех категорий.
     *
     * @return List<Category>
     */
    @Query("Select c from Category c")
    Optional<List<Category>> findAllCategories();

    /**
     * Метод удаления категории по наименованию.
     *
     * @param title - наименование категории
     */
    void deleteAllByTitle(String title);

}
