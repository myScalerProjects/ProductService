package com.example.usingthirdpartyapidemo.services;

import com.example.usingthirdpartyapidemo.dtos.FakeStoreProductDto;
import com.example.usingthirdpartyapidemo.exceptions.CategoryNotFoundException;
import com.example.usingthirdpartyapidemo.exceptions.ProductNotFoundException;
import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;
import com.example.usingthirdpartyapidemo.repository.CategoryRepository;
import com.example.usingthirdpartyapidemo.repository.ProductRepository;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {

        Optional<Product> productOptional= productRepository.findById(id);
        if(productOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product with id: "+id+" does not exist");
        }

        Product product =productOptional.get();
        return product;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepository.findById(id);

        if(productOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product with id:"+id+"doesnot exist");
        }

        Product savedProduct=productOptional.get();

        savedProduct.setTitle(product.getTitle());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setImageURL(product.getImageURL());
        Optional<Category> savedCategoryOptional= categoryRepository.findCategoryByCategoryName(product.getCategory().getCategoryName());
        if(savedCategoryOptional.isPresent())
        {
            savedProduct.setCategory(savedCategoryOptional.get());
        }

        return productRepository.save(savedProduct);
    }

    @Override
    //patch update
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepository.findById(id);

        if(productOptional.isEmpty())
        {
            throw new ProductNotFoundException("Product with id:"+id+"doesnot exist");
        }

        Product savedProduct=productOptional.get();

        if(product.getTitle()!=null)
             savedProduct.setTitle(product.getTitle());
        if(product.getDescription()!=null)
            savedProduct.setDescription(product.getDescription());
        if(product.getPrice()!=null)
            savedProduct.setPrice(product.getPrice());
        if(product.getImageURL()!=null)
            savedProduct.setImageURL(product.getImageURL());
        if(product.getCategory()!=null)
        {
            Optional<Category> savedCategoryOptional= categoryRepository.findCategoryByCategoryName(product.getCategory().getCategoryName());
            if(savedCategoryOptional.isPresent())
            {
                savedProduct.setCategory(savedCategoryOptional.get());
            }
        }

        return productRepository.save(savedProduct);

    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product "+id+" is Deleted";

    }

    @Override
    public Product addProduct(Product product) {

       Category category= product.getCategory();
//        if(category.getId()==null)
//        {
//            Category savedCategory=categoryRepository.save(category);
//            product.setCategory(savedCategory);
//        }
        //this got handled by Cascade.PERSIST meaning every time new category comes, it ll save the new category and then create a product

        Optional<Category> savedCategoryOptional= categoryRepository.findCategoryByCategoryName(category.getCategoryName());
        if(savedCategoryOptional.isPresent())
        {
            product.setCategory(savedCategoryOptional.get());
        }



        return  productRepository.save(product);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductInCategory(String category) throws CategoryNotFoundException {
        Optional<Category> savedCategoryOptional= categoryRepository.findCategoryByCategoryName(category);
        if(savedCategoryOptional.isEmpty())
        {
            throw new CategoryNotFoundException("Category with Category name: "+category+" not found");
        }

        List<Product> products=productRepository.findProductByCategory(savedCategoryOptional.get());
        return products;
    }
}
