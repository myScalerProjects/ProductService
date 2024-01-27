package com.example.usingthirdpartyapidemo.repository;

import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByTitleContaining(String title);
    List<Product> findByCategory_Id(Long id);

    Optional<Product> findById(Long id);
    Product save (Product product);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findProductByCategory(Category category);

}
