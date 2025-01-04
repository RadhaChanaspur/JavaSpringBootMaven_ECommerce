package com.example.productscatelogservice.controllers;

import com.example.productscatelogservice.dtos.CategoryDTO;
import com.example.productscatelogservice.dtos.ProductDTO;
import com.example.productscatelogservice.models.Product;
import com.example.productscatelogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

  //Solution 1 : We are creating our own API's and returning the hardcoded data from controller
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("IPhone 6");
        product1.setDescription("Iphone 6");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        return products;
    }
    /*@GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        Product product = new Product();
        product.setId(id);

        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product)
    {
        return product;
    }*/

    //Solution 2 : Call 3rd Party API to get the product data

    @GetMapping("/products/{id}")
    private ProductDTO getProductById(@PathVariable Long id){
        //we should call FakeStoreProductService methods from here, since 2 class should not be direclty dependent on each other, we have created IProductService interface

        Product product = productService.getProductById(id);
        return ProductDTOFromProduct(product);
    }

    private ProductDTO ProductDTOFromProduct(Product product){
        if(product != null){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setImageUrl(product.getImageUrl());

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(product.getCategory().getCategoryName());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
            return productDTO;
        }
        return null;
    }

}
