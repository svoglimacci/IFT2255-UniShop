package controllers;

import models.*;
import services.ProductService;

import java.io.IOException;
import java.util.*;

public class ProductController {

    private final ProductService productService;
    private final Products products;
    private final Set<Book> bookSet;
    private final Set<Electronic> electronicSet;
    private final Set<LearningMaterial> learningMaterialSet;
    private final Set<OfficeSupply> officeSupplySet;
    private final Set<OfficeFurniture> officeFurnitureSet;


    public ProductController() throws IOException {
        this.productService = new ProductService();
        products = productService.getProducts();

        if (products.getBooks() != null) {
            this.bookSet = new HashSet<>(products.getBooks());
        } else {
            this.bookSet = new HashSet<>();
        }

        if (products.getElectronics() != null) {
            this.electronicSet = new HashSet<>(products.getElectronics());
        } else {
            this.electronicSet = new HashSet<>();
        }

        if (products.getLearningMaterials() != null) {
            this.learningMaterialSet = new HashSet<>(products.getLearningMaterials());
        } else {
            this.learningMaterialSet = new HashSet<>();
        }

        if (products.getOfficeSupplies() != null) {
            this.officeSupplySet = new HashSet<>(products.getOfficeSupplies());
        } else {
            this.officeSupplySet = new HashSet<>();
        }

        if (products.getOfficeFurnitures() != null) {
            this.officeFurnitureSet = new HashSet<>(products.getOfficeFurnitures());
        } else {
            this.officeFurnitureSet = new HashSet<>();
        }
    }

    public List<? extends Product> getProductsByCategory(ProductCategory category) {
        return switch (category) {
            case BOOKS -> this.getBooks();
            case LEARNING_MATERIALS -> this.getLearningMaterials();
            case OFFICE_SUPPLIES -> this.getOfficeSupplies();
            case OFFICE_FURNITURES -> this.getOfficeFurnitures();
            case ELECTRONICS -> this.getElectronics();
        };
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


    // Book
    public Set<UUID> addBook(String name, String description, double price, int quantity, String isbn, String author, String publisher, String publicationYear, String edition, String volume, String genre) {
        if (products.getBooks() == null) {
            products.setBooks(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }
        Book newBook = new Book(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "Book", false, isbn, author, publisher, publicationYear, edition, volume, genre);
        bookSet.add(newBook);
        products.getBooks().add(newBook);


        productService.writeProducts(products);
        return instances;
    }

    // Electronic
    public Set<UUID> addElectronic(String name, String description, double price, int quantity, String brand, String model, String releaseDate, String subCategory) {
        if (products.getElectronics() == null) {
            products.setElectronics(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }

        Electronic newElectronic = new Electronic(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "Electronic", false, brand, model, releaseDate, subCategory);
        electronicSet.add(newElectronic);
        products.getElectronics().add(newElectronic);
        productService.writeProducts(products);
        return instances;
    }

    // OfficeSupply
    public Set<UUID> addOfficeSupply(String name, String description, double price, int quantity, String brand, String model, String subCategory) {
        if (products.getOfficeSupplies() == null) {
            products.setOfficeSupplies(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }
        OfficeSupply newOfficeSupply = new OfficeSupply(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "OfficeSupply", false, brand, model, subCategory);
        officeSupplySet.add(newOfficeSupply);
        products.getOfficeSupplies().add(newOfficeSupply);
        productService.writeProducts(products);
        return instances;
    }

    // OfficeFurniture
    public Set<UUID> addOfficeFurniture(String name, String description, double price, int quantity, String brand, String model, String subCategory) {
        if (products.getOfficeFurnitures() == null) {
            products.setOfficeFurnitures(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }

        OfficeFurniture newOfficeFurniture = new OfficeFurniture(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "OfficeFurniture", false, brand, model, subCategory);
        officeFurnitureSet.add(newOfficeFurniture);
        products.getOfficeFurnitures().add(newOfficeFurniture);

        productService.writeProducts(products);
        return instances;
    }


    // LearningMaterial
    public Set<UUID> addLearningMaterial(String name, String description, double price, int quantity, String brand, String model, String subCategory, String isbn, String author, String organization, String publicationDate, String edition) {
        if (products.getLearningMaterials() == null) {
            products.setLearningMaterials(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }

        LearningMaterial newLearningMaterial = new LearningMaterial(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "LearningMaterial", false, brand, model, subCategory, isbn, author, organization, publicationDate, edition);
        learningMaterialSet.add(newLearningMaterial);
        products.getLearningMaterials().add(newLearningMaterial);

        productService.writeProducts(products);
        return instances;
    }


    public boolean changeAttribute(Product product, String attribute, String value, ProductCategory category) {
        switch (attribute) {
            case "likes":
                if (value == "add") {
                    product.addLike();

                } else {
                    product.removeLike();
                }
                break;
        }
        switch (category) {
            case BOOKS:
                bookSet.add((Book) product);
                products.setBooks(new ArrayList<>(bookSet));
                break;

        }
        productService.writeProducts(products);
        return true;
    }
}