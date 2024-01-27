package com.example.usingthirdpartyapidemo.controllers;

import com.example.usingthirdpartyapidemo.exceptions.CategoryNotFoundException;
import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;
import com.example.usingthirdpartyapidemo.services.ProductService;
import com.example.usingthirdpartyapidemo.services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private ProductService productService;

    @Autowired
    public CategoryController(@Qualifier("selfProductService")ProductService productService) {
        this.productService=productService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return new ResponseEntity<>(productService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Product>> getAllProductInCategory( @PathVariable("categoryName")String categoryName) throws CategoryNotFoundException {
        return new ResponseEntity<>(productService.getAllProductInCategory(categoryName), HttpStatus.OK);
    }
}
