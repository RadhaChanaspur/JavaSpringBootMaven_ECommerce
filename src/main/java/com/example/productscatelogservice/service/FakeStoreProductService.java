package com.example.productscatelogservice.service;

import com.example.productscatelogservice.dtos.FakeStoreProductDTO;
import com.example.productscatelogservice.models.Category;
import com.example.productscatelogservice.models.Product;
import com.example.productscatelogservice.models.SSLByPass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import javax.net.ssl.HttpsURLConnection;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URI;
import org.apache.http.client.utils.URIBuilder;



@Service // this annotation indicates that this is a service layer class which has business logic
//implemented and also creates bean for this

public class FakeStoreProductService implements  IProductService{


    public Product getProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,id).getBody();
//        //here name of variable in path {id} should match with parameter passed for the method
//        //resttemplate object would return JSON body, internally RestTemplate uses Jackson library which has object mapper, so it would automatically convert the response from API to fakestoreproductDTO object
//         return getProductFromFakeStore(fakeStoreProductDTO);


        // Disable SSL validation (temporary workaround since RestTemplate is not working)
        SSLByPass.disableSSLValidation();
        FakeStoreProductDTO product = getDataFromFakeStoreUsingAPICall(id);

        return getProductFromFakeStore(product);
    }

    private  FakeStoreProductDTO getDataFromFakeStoreUsingAPICall(Long id) {
        FakeStoreProductDTO product = null;
        // Now make your HTTPS request
        try {
            URI uri = new URIBuilder("https://fakestoreapi.com/products/" + id).build();
            URL url = uri.toURL();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convert JSON to Java object
            String jsonResponse = response.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(jsonResponse, FakeStoreProductDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    private Product getProductFromFakeStore(FakeStoreProductDTO fakeStoreProductDTO) {
        if(fakeStoreProductDTO != null){
            Product product = new Product();
            product.setId(fakeStoreProductDTO.getId());
            product.setDescription(fakeStoreProductDTO.getDescription());
            product.setImageUrl(fakeStoreProductDTO.getImage());
            product.setPrice(fakeStoreProductDTO.getPrice());
            product.setName(fakeStoreProductDTO.getTitle());

            Category category = new Category();
            category.setCategoryName(fakeStoreProductDTO.getCategory());
            product.setCategory(category);

            return product;
        }
        return null;

    }

}
