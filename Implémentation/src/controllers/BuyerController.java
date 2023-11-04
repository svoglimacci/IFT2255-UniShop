package controllers;

import models.BookGenre;
import models.Products;
import services.BuyerService;

import java.io.IOException;

public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController() throws IOException {
        this.buyerService = new BuyerService();
    }

    public Products getBooksByGenre(String genre) {
        return buyerService.getBooksByGenre(genre);
    }
}