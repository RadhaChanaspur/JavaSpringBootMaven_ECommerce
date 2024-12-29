package com.example.productscatelogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class Category extends BaseModel{
    private String categoryName;
    private String description;
    private List<Product> products;
}
