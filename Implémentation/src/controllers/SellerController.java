package controllers;

import services.ProductService;

import java.io.IOException;

public class SellerController {

    private final ProductService productService;

    public SellerController() throws IOException {
        this.productService = new ProductService();
    }

    public boolean addProduct(String name, String description, double price, int quantity, String brand, String model, String subCategory, String isbn, String author, String organization, String publicationDate, String edition) {
        if (name.isEmpty() || description.isEmpty() || price < 0 || quantity < 0 || brand.isEmpty() || model.isEmpty() || subCategory.isEmpty() || isbn.isEmpty() || author.isEmpty() || organization.isEmpty() || publicationDate.isEmpty() || edition.isEmpty()) {
            return false;
        }
        return this.productService.addProduct(name, description, price, quantity, brand, model, subCategory, isbn, author, organization, publicationDate, edition);
    }
}