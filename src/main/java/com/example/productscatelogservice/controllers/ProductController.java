package com.example.productscatelogservice.controllers;

import com.example.productscatelogservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

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
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        Product product = new Product();
        product.setId(id);

        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product)
    {
        return product;
    }

}
