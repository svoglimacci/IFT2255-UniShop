package views;

import controllers.ProductController;
import controllers.UserController;
import models.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class SellerView {

    private final UserController userController;
    private final ProductController productController;
    private final Seller user;
    private final String username;

    public SellerView(Seller user, UserController userController, ProductController productController) {
        this.userController = userController;
        this.productController = productController;
        this.user = user;
        this.username = user.getUsername();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean displayMenu = true;
        while (true) {
            if (displayMenu) {
                System.out.println("Veuillez choisir une option :");
                System.out.println("1. Ajouter un produit");
                System.out.println("2. Modifier le profil");
                System.out.println("3. Mes commandes");
                System.out.println("0. Se déconnecter");

            }

            try {
                input = sc.nextLine();
                switch (input) {
                    case "1" -> showProductPrompt(sc);
                    case "2" -> modifyProfile(sc);
                    case "3" -> showOrders(sc);
                    case "0" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Choix invalide");
                    displayMenu = false; }
                }
            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
        }
    }

    public void showProductPrompt(Scanner sc) {
        String input;
        System.out.println("Veuillez entrer le nom du produit:");
        String name = sc.nextLine();
        System.out.println("Veuillez entrer la description du produit:");
        String description = sc.nextLine();
        System.out.println("Veuillez entrer le prix:");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Veuillez entrer la quantité:");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.println("Veuillez choisir une catégorie :");
                System.out.println("1. Livres et Manuels");
                System.out.println("2. Matériel Informatique");
                System.out.println("3. Ressources d'apprentissage");
                System.out.println("4. Articles de papeterie");
                System.out.println("5. Équipêment de bureau");

                    try {
                input = sc.nextLine();
                switch (input) {
                    case "1" -> showBookPrompt(sc, name, description, price, quantity);
                    case "2" -> showElectronicPrompt(sc, name, description, price, quantity);
                    case "3" -> showLearningMaterialPrompt(sc, name, description, price, quantity);
                    case "4" -> showOfficeSupplyPrompt(sc, name, description, price, quantity);
                    case "5" -> showOfficeFurniturePrompt(sc, name, description, price, quantity);
                    default -> {
                        System.out.println("Choix invalide");
                    }
                }

            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
    }

    public void showBookPrompt(Scanner sc, String name, String description, double price, int quantity) {
        Book addProduct;
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
        if (!addProduct.instances.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct.instances);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showOfficeFurniturePrompt(Scanner sc, String name, String description, double price, int quantity) {
        OfficeFurniture addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie:");
        String subCategory = sc.nextLine();

        addProduct = this.productController.addOfficeFurniture(name, description, price, quantity, brand, model, subCategory);
        if (!addProduct.instances.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct.instances);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showOfficeSupplyPrompt(Scanner sc, String name, String description, double price, int quantity) {
        OfficeSupply addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie:");
        String subCategory = sc.nextLine();

        addProduct = this.productController.addOfficeSupply(name, description, price, quantity, brand, model, subCategory);
        if (!addProduct.instances.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct.instances);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showElectronicPrompt(Scanner sc, String name, String description, double price, int quantity) {
        Electronic addProduct;
        System.out.println("Veuillez entrer la marque:");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle:");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la date de sortie:");
        String releaseDate = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie:");
        String subCategory = sc.nextLine();

        addProduct = this.productController.addElectronic(name, description, price, quantity, brand, model, releaseDate, subCategory);
        if (!addProduct.instances.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct.instances);
            this.start();
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }
    }

    public void showLearningMaterialPrompt(Scanner sc, String name, String description, double price, int quantity) {
        LearningMaterial addProduct;
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
        if (!addProduct.instances.isEmpty()) {
            System.out.println("Produit ajouté avec succès");
            this.userController.addProductsToSeller(this.username, addProduct.instances);
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
                    return;
                default:
                    System.out.println("Choix invalide");
            }
            if (modify) {
                System.out.println("Modification effectuée avec succès");

            } else {
                System.out.println("Erreur lors de la modification");
            }
        } catch (Exception e) {
            System.out.println("Choix invalide");
        }

    }

    private void showOrders(Scanner sc) {
        List<Order> orders = user.getOrders();
        String input;
        if (orders.isEmpty()) {
            System.out.println("Aucune commande reçue");
            return;
        }

        int idx = 1;

        for (Order order : orders) {
            System.out.println(idx + ". " + order.getId() + " | " + order.getStatus());
            idx++;
        }

        System.out.println("Veuillez choisir une option :");
        System.out.println("#. Entrer le numéro de la commande pour voir les détails");
        System.out.println("0. Retour");


        while (true) {
            try {
                input = sc.nextLine();
                int orderChoice = Integer.parseInt(input);
                if (input.equals("0")) {
                    return;
                }
                if (orderChoice >= 0 && orderChoice < idx) {
                    Order order = user.getOrders().get(orderChoice - 1);
                    System.out.println(order.getId() + " | " + order.getPrice() + " | " + order.getStatus());
                    System.out.println("Date de livraison estimée : " + LocalDate.now().plusDays(3));

                    System.out.println("\nProduits :");
                    for (UUID id : order.getProductsId()) {
                        Product product = productController.getProductById(id);
                        System.out.println(product.productToString());
                    }

                    System.out.println("\nProblèmes :");
                    for (Issue issue : order.getIssues()) {
                        System.out.println(issue.getDescription());
                    }

                    System.out.println("Veuillez choisir une option :");
                    System.out.println("1. Changer l'état de livraison à: en livraison");
                    System.out.println("2. Annuler la commande");
                    System.out.println("3. Proposer une solution");
                    System.out.println("0. Retour");
                    input = sc.nextLine();
                    if(Objects.equals(input, "1")){
                        order.changeOrderStatus(Order.orderState.delivering);
                    }
                    else{
                        return;
                    }
                } else {
                    System.out.println("Choix invalide");
                }

            } catch (NumberFormatException e) {
                System.out.println("Choix invalide");
            }
        }
    }
}