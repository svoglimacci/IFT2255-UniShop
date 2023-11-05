package services;

import models.*;
import utilities.JSONHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuyerService {

    private final Set<Book> bookSet;
    private final Set<Electronic> electronicSet;
    private final Set<LearningMaterial> learningMaterialSet;
    private final Set<OfficeSupply> officeSupplySet;
    private final Set<OfficeFurniture> officeFurnitureSet;
    private final Products products;
    private final JSONHandler JSONHandler;

    public BuyerService() throws IOException {
        JSONHandler = new JSONHandler();
        products = JSONHandler.readJsonFromFile("src/data/products.json", Products.class);
        this.bookSet = new HashSet<>(products.getBooks());
        this.electronicSet = new HashSet<>(products.getElectronics());
        this.learningMaterialSet = new HashSet<>(products.getLearningMaterials());
        this.officeSupplySet = new HashSet<>(products.getOfficeSupplies());
        this.officeFurnitureSet = new HashSet<>(products.getOfficeFurnitures());

    }

    public List<Book> getBooks() {
        return new ArrayList<>(bookSet);
    }

    public List<Electronic> getElectronics() {
        return new ArrayList<>(electronicSet);
    }

    public List<LearningMaterial> getLearningMaterials() {
        return new ArrayList<>(learningMaterialSet);
    }

    public List<OfficeSupply> getOfficeSupplies() {
        return new ArrayList<>(officeSupplySet);
    }

    public List<OfficeFurniture> getOfficeFurnitures() {
        return new ArrayList<>(officeFurnitureSet);
    }

}