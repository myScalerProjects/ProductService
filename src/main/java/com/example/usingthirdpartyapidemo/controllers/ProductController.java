package com.example.usingthirdpartyapidemo.controllers;

import com.example.usingthirdpartyapidemo.dtos.FakeStoreProductDto;
import com.example.usingthirdpartyapidemo.exceptions.CategoryNotFoundException;
import com.example.usingthirdpartyapidemo.exceptions.ProductNotFoundException;
import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;
import com.example.usingthirdpartyapidemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService")ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product> >getAllProducts()
    {
       ResponseEntity<List<Product>> response= new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
         return new ResponseEntity<>(productService.getSingleProduct(id),HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category> >getAllCategories()
    {
        ResponseEntity<List<Category>> response= new ResponseEntity<>(productService.getAllCategories(), HttpStatus.OK);
        return response;
    }

//    @PostMapping() // for  adding fakeStorePRoduct
//    public ResponseEntity<Product> addNewProduct(@RequestBody FakeStoreProductDto fakeStoreProductDto)
//    {
//       return new ResponseEntity<>(productService.addProduct(fakeStoreProductDto),HttpStatus.OK);
//    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product)
    {
       return new ResponseEntity<>(productService.addProduct(product),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<Product>(productService.updateProduct(id,product),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<Product>(productService.replaceProduct(id,product),HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id)
    {
          return new ResponseEntity<String>(productService.deleteProduct(id),HttpStatus.OK);
    }




}