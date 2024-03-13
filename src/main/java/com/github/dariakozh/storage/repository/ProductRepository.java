package com.github.dariakozh.storage.repository;


import com.github.dariakozh.storage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
//    Optional<Category> findByTitle(String title);
//
//    @Query("Select c from Category c")
//    Optional<List<Category>> findAllCategories();
//
//    void deleteAllByTitle(String title);

}
