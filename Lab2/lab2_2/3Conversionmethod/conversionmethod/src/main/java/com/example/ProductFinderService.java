package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;

public class ProductFinderService {
    private final ISimpleHttpClient httpClient;
    private final String API_URL = "https://fakestoreapi.com/products/";

    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(Integer id) {
        try {
            String jsonResponse = httpClient.doHttpGet(API_URL + id);
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(jsonResponse, Product.class);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty(); 
        }
    }
}
