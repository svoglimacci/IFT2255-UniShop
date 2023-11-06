package views;

import controllers.ProductController;
import controllers.UserController;
import models.ProductCategory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SellerView {

    private final UserController userController;
    private final ProductController productController;
    private final String username;

    public SellerView(String username) throws IOException {
        this.userController = new UserController();
        this.productController = new ProductController();
        this.username = username;
    }

    public void start() {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier le profil");
        System.out.println("0. Se déconnecter");
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        try {
            switch (input) {
                case "1":
                    this.showProductPrompt(sc);
                    break;
                case "2":
                    this.modifyProfile(sc);
                    break;
                case "0":
                    AuthView authView = new AuthView();
                    authView.start();
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProductPrompt(Scanner sc) {
        System.out.println("Veuillez entrer le nom du produit:");
        String name = sc.nextLine();
        System.out.println("Veuillez entrer la description du produit:");
        String description = sc.nextLine();
        System.out.println("Veuillez entrer le prix:");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Veuillez entrer la quantité:");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.println(ProductCategory.categoriesToString());
        int categoryIndex = Integer.parseInt(sc.nextLine());
        if (categoryIndex == 0) {
            this.start();
        } else if (categoryIndex < 0 || categoryIndex > ProductCategory.values().length) {
            System.out.println("Choix invalide");
            this.showProductPrompt(sc);
        }
        ProductCategory category = ProductCategory.values()[categoryIndex - 1];
        switch (category) {
            case BOOKS -> showBookPrompt(sc, name, description, price, quantity);
            case LEARNING_MATERIALS -> showLearningMaterialPrompt(sc, name, description, price, quantity);
            case OFFICE_FURNITURES -> showOfficeFurniturePrompt(sc, name, description, price, quantity);
            case OFFICE_SUPPLIES -> showOfficeSupplyPrompt(sc, name, description, price, quantity);
            case ELECTRONICS -> showElectronicPrompt(sc, name, description, price, quantity);
        }
    }

    public void showBookPrompt(Scanner sc, String name, String description, double price, int quantity) {
        List<UUID> addProduct;
        System.out.println("Veuillez entrer l'ISBN:");
        String isbn = sc.nextLine();
        System.out.println("Veuillez entrer l'auteur:");
        String author = sc.nextLine();
        System.out.println("Veuillez entrer l'éditeur:");
        String publisher = sc.nextLine();
        System.out.println("Veuillez entrer l'année de publication:");
        String publicationYear = sc.nextLine();
        System.out.println("Veuillez entrer l'édition:");
        String edition = sc.nextLine();
        System.out.println("Veuillez entrer le volume:");
        String volume = sc.nextLine();
        System.out.println("Veuillez entrer le genre:");
        String genre = sc.nextLine();

        addProduct = this.productController.addBook(name, description, price, quantity, isbn, author, publisher, publicationYear, edition, volume, genre);
        if (!addProduct.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showOfficeFurniturePrompt(Scanner sc, String name, String description, double price, int quantity) {
        List<UUID> addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie:");
        String subCategory = sc.nextLine();

        addProduct = this.productController.addOfficeFurniture(name, description, price, quantity, brand, model, subCategory);
        if (!addProduct.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showOfficeSupplyPrompt(Scanner sc, String name, String description, double price, int quantity) {
        List<UUID> addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie:");
        String subCategory = sc.nextLine();

        addProduct = this.productController.addOfficeSupply(name, description, price, quantity, brand, model, subCategory);
        if (!addProduct.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showElectronicPrompt(Scanner sc, String name, String description, double price, int quantity) {
        List<UUID> addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la date de sortie:");
        String releaseDate = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie:");
        String subCategory = sc.nextLine();

        addProduct = this.productController.addElectronic(name, description, price, quantity, brand, model, releaseDate, subCategory);
        if (!addProduct.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showLearningMaterialPrompt(Scanner sc, String name, String description, double price, int quantity) {
        List<UUID> addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer le type (imprimé ou électronique):");
        String subCategory = sc.nextLine();
        System.out.println("Veuillez entrer l'auteur:");
        String author = sc.nextLine();
        System.out.println("Veuillez entrer l'ISBN:");
        String isbn = sc.nextLine();
        System.out.println("Veuillez entrer l'organisation:");
        String organization = sc.nextLine();
        System.out.println("Veuillez entrer la date de publication:");
        String publicationDate = sc.nextLine();
        System.out.println("Veuillez entrer l'édition:");
        String edition = sc.nextLine();

        addProduct = this.productController.addLearningMaterial(name, description, price, quantity, brand, model, subCategory, isbn, author, organization, publicationDate, edition);
        if (!addProduct.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    private void modifyProfile(Scanner sc) {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Modifier le nom d'entreprise");
        System.out.println("2. Modifier l'adresse");
        System.out.println("3. Modifier le numéro de téléphone");
        System.out.println("4. Modifier le mot de passe");
        System.out.println("0. Retour");
        String input;
        boolean modify = false;
        input = sc.nextLine();
        try {
            switch (input) {
                case "1":
                    System.out.println("Veuillez entrer le nom d'entreprise :");
                    String businessName = sc.nextLine();
                    modify = this.userController.changeProperty("businessName", businessName, this.username, true);
                    break;
                case "2":
                    System.out.println("Veuillez entrer la nouvelle adresse :");
                    String address = sc.nextLine();
                    modify = this.userController.changeProperty("address", address, this.username, true);
                    break;
                case "3":
                    System.out.println("Veuillez entrer le nouveau numéro de téléphone :");
                    String phoneNumber = sc.nextLine();
                    modify = this.userController.changeProperty("phoneNumber", phoneNumber, this.username, true);
                    break;
                case "4":
                    System.out.println("Veuillez entrer le nouveau mot de passe :");
                    String password = sc.nextLine();
                    modify = this.userController.changeProperty("password", password, this.username, true);
                    break;
                case "0":
                    this.start();
                    break;
                default:
                    System.out.println("Choix invalide");
            }
            if (modify) {
                System.out.println("Modification effectuée avec succès");
                this.start();
            } else {
                System.out.println("Erreur lors de la modification");
                this.modifyProfile(sc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}