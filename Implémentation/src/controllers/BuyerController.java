package controllers;

import models.*;
import services.BuyerService;

import java.io.IOException;
import java.util.List;

public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController() throws IOException {
        this.buyerService = new BuyerService();
    }


    public List<Book> getBooks() {
        return this.buyerService.getBooks();
    }

    public List<Electronic> getElectronics() {
        return this.buyerService.getElectronics();
    }

    public List<LearningMaterial> getLearningMaterials() {
        return this.buyerService.getLearningMaterials();
    }

    public List<OfficeSupply> getOfficeSupplies() {
        return this.buyerService.getOfficeSupplies();
    }

    public List<OfficeFurniture> getOfficeFurnitures() {
        return this.buyerService.getOfficeFurnitures();
    }


}