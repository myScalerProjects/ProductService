package com.example.usingthirdpartyapidemo.services;

import com.example.usingthirdpartyapidemo.dtos.FakeStoreProductDto;
import com.example.usingthirdpartyapidemo.exceptions.CategoryNotFoundException;
import com.example.usingthirdpartyapidemo.exceptions.ProductNotFoundException;
import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    String deleteProduct(Long id);

    Product addProduct(Product product);

    List<Category> getAllCategories();

    List<Product> getAllProductInCategory(String category) throws CategoryNotFoundException;
}
