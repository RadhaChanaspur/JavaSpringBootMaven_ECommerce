package com.example.productscatelogservice.controllers;

import com.example.productscatelogservice.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public List<Category> getallCategories(){
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("Gadgets");
        category.setDescription("Gadgets");

        List<Category> categories = new ArrayList<Category>();
        categories.add(category);
        return categories;
    }

    @GetMapping("/categories/{categoryid}")
    public Category getCategoryById(@PathVariable("categoryid") Long id){
        //If we are different parameter name in URL path and method parameter list, then
        //we can specify that inside PathVariable
        Category category = new Category();
        category.setId(id);
        category.setCategoryName("Gadgets");
        return category;
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category){
        //RequestBody is used to map the body sent in API to category. Based on the keys provided
        //in the request body it maps it to the appropriate model
        return category;
    }
}
