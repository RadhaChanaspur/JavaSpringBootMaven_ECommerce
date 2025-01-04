package com.example.productscatelogservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    // this class fields are created exactly same as the response received by fakestore api.
    //since we are not using rating from the api response, we are not adding that field

    //fake store API Response - https://fakestoreapi.com/products/4

    /*{
            "id":4,
            "title":"Mens Casual Slim Fit",
            "price":15.99,
            "description":
                 "The color could be slightly different between on the screen and in practice. / Please note that body builds vary by person, therefore, detailed size information should be reviewed below on the product description.",
            "category":"men's clothing",
            "image":"https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg",
            "rating":{
                "rate":2.1,
                "count":430
                }
    }*/
}
