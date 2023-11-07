package services;

import models.Products;
import utilities.JSONHandler;

import java.io.IOException;


public class ProductService {
    private final JSONHandler JSONHandler;

    public ProductService() {
        JSONHandler = new JSONHandler();
    }

    public Products getProducts() throws IOException {
        return JSONHandler.readJsonFromFile("src/data/products.json", Products.class);
    }

    public void writeProducts(Products products) {
        try {
            JSONHandler.writeJsonToFile(products, "src/data/products.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}