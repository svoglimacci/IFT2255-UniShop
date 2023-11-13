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

    public List<? extends Product> getProductsByCategory(String category) {
        return switch (category) {
            case "Books" -> this.getBooks();
            case "LearningMaterials" -> this.getLearningMaterials();
            case "OfficeSupplies" -> this.getOfficeSupplies();
            case "OfficeFurnitures" -> this.getOfficeFurnitures();
            case "Electronics" -> this.getElectronics();
            default -> throw new IllegalStateException("Unexpected value: " + category);
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


    public boolean changeAttribute(Product product, String attribute, String value) {
        String category = product.getClass().getSimpleName();
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
            case "Books":
                bookSet.add((Book) product);
                products.setBooks(new ArrayList<>(bookSet));
                break;
                //TODO: add other categories

        }
        productService.writeProducts(products);
        return true;
    }

public List<Product> searchProducts(String keyword) {
    List<Product> filteredProducts = new ArrayList<>();

    if (products.getBooks() != null) {
        for (Product product : products.getBooks()) {
            if (product.getName().contains(keyword)) {
                filteredProducts.add(product);
            }
        }
    }

    if (products.getElectronics() != null) {
        for (Product product : products.getElectronics()) {
            if (product.getName().contains(keyword)) {
                filteredProducts.add(product);
            }
        }
    }

    if (products.getLearningMaterials() != null) {
        for (Product product : products.getLearningMaterials()) {
            if (product.getName().contains(keyword)) {
                filteredProducts.add(product);
            }
        }
    }

    if (products.getOfficeSupplies() != null) {
        for (Product product : products.getOfficeSupplies()) {
            if (product.getName().contains(keyword)) {
                filteredProducts.add(product);
            }
        }
    }

    if (products.getOfficeFurnitures() != null) {
        for (Product product : products.getOfficeFurnitures()) {
            if (product.getName().contains(keyword)) {
                filteredProducts.add(product);
            }
        }
    }

    return filteredProducts;
}

    public List<Product> sortProducts(List<Product> searchResults, String sortBy) {
        switch (sortBy) {
            case "price":
                searchResults.sort(Comparator.comparing(Product::getPrice));
                break;
            case "rating":
                searchResults.sort(Comparator.comparing(Product::getRating));
                break;
            case "likes":
                searchResults.sort(Comparator.comparing(Product::getLikes));
                break;
        }
        return searchResults;
    }

    public Product getProductById(UUID id) {
        if (products.getBooks() != null) {
            for (Product product : products.getBooks()) {
                if (product.getId().equals(id)) {
                    return product;
                }
            }
        }

        if (products.getElectronics() != null) {
            for (Product product : products.getElectronics()) {
                if (product.getId().equals(id)) {
                    return product;
                }
            }
        }

        if (products.getLearningMaterials() != null) {
            for (Product product : products.getLearningMaterials()) {
                if (product.getId().equals(id)) {
                    return product;
                }
            }
        }

        if (products.getOfficeSupplies() != null) {
            for (Product product : products.getOfficeSupplies()) {
                if (product.getId().equals(id)) {
                    return product;
                }
            }
        }

        if (products.getOfficeFurnitures() != null) {
            for (Product product : products.getOfficeFurnitures()) {
                if (product.getId().equals(id)) {
                    return product;
                }
            }
        }

        return null;
    }
}