package com.example.productscatelogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private CategoryDTO category;

}
