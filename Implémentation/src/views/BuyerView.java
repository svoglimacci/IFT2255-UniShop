package views;

import controllers.ProductController;
import controllers.UserController;
import models.Product;
import models.ProductCategory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BuyerView {

    private final ProductController productController;

    private final UserController userController;
    private final String username;

    public BuyerView(String username) throws IOException {
        this.productController = new ProductController();
        this.userController = new UserController();
        this.username = username;
    }

    public void start() {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Afficher les produits");
        System.out.println("2. Modifier le profil");
        System.out.println("0. Se déconnecter");
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            try {
                switch (input) {
                    case "1":
                        this.showCategories(sc);
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
        } while (!input.equals("exit"));
    }

    public void showCategories(Scanner sc) {
        System.out.println("Veuillez choisir une catégorie :");
        System.out.println(ProductCategory.categoriesToString());
        System.out.println("0. Retour");
        int categoryIndex = Integer.parseInt(sc.nextLine());
        if (categoryIndex == 0) {
            this.start();
        } else if (categoryIndex < 0 || categoryIndex > ProductCategory.values().length) {
            System.out.println("Choix invalide");
            this.showCategories(sc);
        }
        ProductCategory category = ProductCategory.values()[categoryIndex - 1];
        showProducts(sc, category);
    }

    public void showProducts(Scanner sc, ProductCategory category) {
        int idx = 1;
        List<? extends Product> products = this.productController.getProductsByCategory(category);
        if (products.isEmpty()) {
            System.out.println("Aucun produit dans cette catégorie");
            this.showCategories(sc);
        } else {
            System.out.println("Veuillez choisir un produit :");
            System.out.println("0. Retour");
            for (Product product : products) {
                System.out.println(product.productToString());
                idx++;
            }
            String input = sc.nextLine();
            try {
                if (input.equals("0")) {
                    this.showCategories(sc);
                } else {
                    int productChoice = Integer.parseInt(input);
                    if (productChoice > 0 && productChoice < idx) {
                        this.showProduct(products.iterator().next(), sc, category);
                    } else {
                        System.out.println("Choix invalide");
                        this.showProducts(sc, category);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void showProduct(Product product, Scanner sc, ProductCategory category) {
        boolean liked = false;
        System.out.println(product.productDetailsToString());
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Ajouter une mention j'aime");
        String input;
        input = sc.nextLine();
        try {
            switch (input) {
                case "1":
                    liked = this.productController.changeAttribute(product, "likes", null, category);
                    if (liked) {
                        System.out.println("Mention j'aime ajoutée avec succès");
                    } else {
                        System.out.println("Erreur lors de l'ajout de la mention j'aime");
                    }
                    this.showProduct(product, sc, category);
                    break;
                case "0":
                    this.showProducts(sc, category);
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyProfile(Scanner sc) {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Modifier le prénom");
        System.out.println("2. Modifier le nom");
        System.out.println("3. Modifier l'adresse");
        System.out.println("4. Modifier le numéro de téléphone");
        System.out.println("5. Modifier le mot de passe");
        System.out.println("0. Retour");
        String input;
        boolean modify = false;
        input = sc.nextLine();
        try {
            switch (input) {
                case "1":
                    System.out.println("Veuillez entrer le nouveau prénom :");
                    String firstName = sc.nextLine();
                    modify = this.userController.changeProperty("firstName", firstName, this.username, false);
                    break;
                case "2":
                    System.out.println("Veuillez entrer le nouveau nom :");
                    String lastName = sc.nextLine();
                    modify = this.userController.changeProperty("lastName", lastName, this.username, false);
                    break;
                case "3":
                    System.out.println("Veuillez entrer la nouvelle adresse :");
                    String address = sc.nextLine();
                    modify = this.userController.changeProperty("address", address, this.username, false);
                    break;
                case "4":
                    System.out.println("Veuillez entrer le nouveau numéro de téléphone :");
                    String phoneNumber = sc.nextLine();
                    modify = this.userController.changeProperty("phoneNumber", phoneNumber, this.username, false);
                    break;
                case "5":
                    System.out.println("Veuillez entrer le nouveau mot de passe :");
                    String password = sc.nextLine();
                    modify = this.userController.changeProperty("password", password, this.username, false);
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