package com.example.usingthirdpartyapidemo.services;

import com.example.usingthirdpartyapidemo.dtos.FakeStoreCategoryDto;
import com.example.usingthirdpartyapidemo.dtos.FakeStoreProductDto;
import com.example.usingthirdpartyapidemo.exceptions.ProductNotFoundException;
import com.example.usingthirdpartyapidemo.models.Category;
import com.example.usingthirdpartyapidemo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {

      FakeStoreProductDto productDto= restTemplate.getForObject(
               "https://fakestoreapi.com/products/"+id,
               FakeStoreProductDto.class);

      if(productDto==null)
      {
          throw new ProductNotFoundException("Product with id: "+id+" does not exist");
      }

      return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response=restTemplate.getForObject
                                       ("https://fakestoreapi.com/products",
                                         FakeStoreProductDto[].class);

        ArrayList<Product> answer= new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:response)
        {
            answer.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return  answer;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response=restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.POST, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(response);
    }
    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto response= restTemplate.patchForObject(
                "https://fakestoreapi.com/products/7",product, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(response);
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }


    //@Override
//    public Product deleteProduct(Long id) {
////        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
////        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
////        FakeStoreProductDto response= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.GET, requestCallback, responseExtractor);
////        return convertFakeStoreProductDtoToProduct(response);
//
//        FakeStoreProductDto productDto= restTemplate.getForObject(
//                "https://fakestoreapi.com/products/"+id,
//                FakeStoreProductDto.class);
//
//        return convertFakeStoreProductDtoToProduct(productDto);
//    }

    @Override
    public Product addProduct(Product product) {

        FakeStoreProductDto fakeStoreProductDto=convertProductToFakeStoreProductDto(product);
        FakeStoreProductDto response= restTemplate.postForObject("https://fakestoreapi.com/products",
                                                                     fakeStoreProductDto,
                                                                     FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(response);
    }

    @Override
    public List<Category> getAllCategories() {
        String[] response=restTemplate.getForObject
                ("https://fakestoreapi.com/products/categories",
                       String[].class);

        ArrayList<Category> answer= new ArrayList<>();
        for(String categoryName:response)
        {
            answer.add(convertFakeStoreCategoryDtoToCategory(categoryName));
        }
        return  answer;
    }

    @Override
    public List<Product> getAllProductInCategory(String categoryName) {
        FakeStoreProductDto[] response=restTemplate.getForObject
                ("https://fakestoreapi.com/products/category/"+categoryName,
                        FakeStoreProductDto[].class);

        ArrayList<Product> answer= new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:response)
        {
            answer.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return  answer;
    }

    private Category convertFakeStoreCategoryDtoToCategory(String categoryNAme)
    {
        Category category=new Category();
        category.setCategoryName(categoryNAme);
        return category;
    }


    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());

        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImage());
        product.setPrice(productDto.getPrice());

        product.setCategory(new Category());
        product.getCategory().setCategoryName(productDto.getCategory());

        return product;

    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(product.getTitle());

        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageURL());
        fakeStoreProductDto.setPrice(product.getPrice());

        String categoryName=product.getCategory().getCategoryName();
        fakeStoreProductDto.setCategory(categoryName);


        return fakeStoreProductDto;

    }



}
