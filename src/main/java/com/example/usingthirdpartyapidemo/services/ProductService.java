package com.example.usingthirdpartyapidemo.services;

import com.example.usingthirdpartyapidemo.dtos.FakeStoreProductDto;
import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);
    Product updateProduct(Long id, Product product);

    Product deleteProduct(Long id);

    Product addProduct(FakeStoreProductDto fakeStoreProductDto);

    List<Category> getAllCategories();

    List<Product> getAllProductInCategory(String category);
}
