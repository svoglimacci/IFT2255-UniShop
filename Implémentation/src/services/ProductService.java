package services;

import models.*;
import utilities.JSONHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductService {

    private final Set<Book> bookSet;
    private final Set<Electronic> electronicSet;
    private final Set<LearningMaterial> learningMaterialSet;
    private final Set<OfficeSupply> officeSupplySet;
    private final Set<OfficeFurniture> officeFurnitureSet;
    private final Products products;
    private final JSONHandler JSONHandler;

    public ProductService() throws IOException {
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

    public boolean addProduct(String name, String description, double price, int quantity, String brand, String model, String subCategory, String isbn, String author, String organization, String publicationDate, String edition) {
        try {
            LearningMaterial newLearningMaterial = new LearningMaterial(name, description, price, quantity, 0, new ArrayList<>(), 0, "Ressources d'apprentissage", false, brand, model, subCategory, isbn, author, organization, publicationDate, edition);
            learningMaterialSet.add(newLearningMaterial);
            products.getLearningMaterials().add(newLearningMaterial);
            JSONHandler.writeJsonToFile(products, "src/data/products.json");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}