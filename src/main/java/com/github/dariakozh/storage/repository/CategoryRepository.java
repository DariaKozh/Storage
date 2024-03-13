package com.github.dariakozh.storage.repository;

import com.github.dariakozh.storage.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByTitle(String title);

    @Query("Select c from Category c")
    Optional<List<Category>> findAllCategories();

//    @Query("Delete c from Category c where c.title = :title")
    void deleteAllByTitle(String title);

}
