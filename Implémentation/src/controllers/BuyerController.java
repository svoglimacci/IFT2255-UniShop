package controllers;

import models.Product;
import models.ProductCategory;
import services.BuyerService;

import java.io.IOException;

public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController() throws IOException {
        this.buyerService = new BuyerService();
    }


    public Iterable<? extends Product> getProductsByCategory(ProductCategory category) {
        return switch (category) {
            case BOOKS -> this.buyerService.getBooks();
            case LEARNING_MATERIALS -> this.buyerService.getLearningMaterials();
            case OFFICE_SUPPLIES -> this.buyerService.getOfficeSupplies();
            case OFFICE_FURNITURES -> this.buyerService.getOfficeFurnitures();
            case ELECTRONICS -> this.buyerService.getElectronics();
        };
    }


}