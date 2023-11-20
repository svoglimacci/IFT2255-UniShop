package controllers;

import models.*;

import java.util.*;

public class ProductController {

    private final Products products = new Products(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());



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
        return products.getBooks();
    }

    public List<Electronic> getElectronics() {
        return products.getElectronics();
    }

    public List<LearningMaterial> getLearningMaterials() {
        return products.getLearningMaterials();
    }

    public List<OfficeSupply> getOfficeSupplies() {
        return products.getOfficeSupplies();
    }

    public List<OfficeFurniture> getOfficeFurnitures() {
       return products.getOfficeFurnitures();
    }


    // Book
    public Book addBook(String name, String description, double price, int quantity, String isbn, String author, String publisher, String publicationYear, String edition, String volume, String genre) {
        if (products.getBooks() == null) {
            products.setBooks(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }
        Book newBook = new Book(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "Book", false, isbn, author, publisher, publicationYear, edition, volume, genre);
        products.getBooks().add(newBook);


        return newBook;
    }

    // Electronic
    public Electronic addElectronic(String name, String description, double price, int quantity, String brand, String model, String releaseDate, String subCategory) {
        if (products.getElectronics() == null) {
            products.setElectronics(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }

        Electronic newElectronic = new Electronic(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "Electronic", false, brand, model, releaseDate, subCategory);
        products.getElectronics().add(newElectronic);

        return newElectronic;
    }

    // OfficeSupply
    public OfficeSupply addOfficeSupply(String name, String description, double price, int quantity, String brand, String model, String subCategory) {
        if (products.getOfficeSupplies() == null) {
            products.setOfficeSupplies(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }
        OfficeSupply newOfficeSupply = new OfficeSupply(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "OfficeSupply", false, brand, model, subCategory);

        products.getOfficeSupplies().add(newOfficeSupply);

        return newOfficeSupply;
    }

    // OfficeFurniture
    public OfficeFurniture addOfficeFurniture(String name, String description, double price, int quantity, String brand, String model, String subCategory) {
        if (products.getOfficeFurnitures() == null) {
            products.setOfficeFurnitures(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }

        OfficeFurniture newOfficeFurniture = new OfficeFurniture(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "OfficeFurniture", false, brand, model, subCategory);

        products.getOfficeFurnitures().add(newOfficeFurniture);


        return newOfficeFurniture;
    }


    // LearningMaterial
    public LearningMaterial addLearningMaterial(String name, String description, double price, int quantity, String brand, String model, String subCategory, String isbn, String author, String organization, String publicationDate, String edition) {
        if (products.getLearningMaterials() == null) {
            products.setLearningMaterials(new ArrayList<>());
        }
        Set<UUID> instances = new HashSet<>();
        for (int i = 0; i < quantity; i++) {
            UUID uuid = UUID.randomUUID();
            instances.add(uuid);
        }

        LearningMaterial newLearningMaterial = new LearningMaterial(UUID.randomUUID(), instances, name, description, price, 0, new ArrayList<>(), 0, "LearningMaterial", false, brand, model, subCategory, isbn, author, organization, publicationDate, edition);

        products.getLearningMaterials().add(newLearningMaterial);


        return newLearningMaterial;
    }


    public boolean changeAttribute(Product product, String attribute, String value) {
        switch (attribute) {
            case "likes":
                if (Objects.equals(value, "add")) {
                    product.addLike();

                } else {
                    product.removeLike();
                }
                break;
        }
        //TODO : add other attributes

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

    public Products getProducts() {
        return products;
    }
}