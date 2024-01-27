package com.example.usingthirdpartyapidemo.repository;

import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category save(Category category);
    List<Category> findAll();



    Optional<Category> findCategoryByCategoryName(String categoryName);
}
