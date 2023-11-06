package controllers;

import models.Product;
import models.ProductCategory;
import services.ProductService;

import java.io.IOException;
import java.util.List;

public class BuyerController {

    private final ProductService productService;

    public BuyerController() throws IOException {
        this.productService = new ProductService();


    }


    public List<? extends Product> getProductsByCategory(ProductCategory category) {
        return switch (category) {
            case BOOKS -> this.productService.getBooks();
            case LEARNING_MATERIALS -> this.productService.getLearningMaterials();
            case OFFICE_SUPPLIES -> this.productService.getOfficeSupplies();
            case OFFICE_FURNITURES -> this.productService.getOfficeFurnitures();
            case ELECTRONICS -> this.productService.getElectronics();
        };
    }


}