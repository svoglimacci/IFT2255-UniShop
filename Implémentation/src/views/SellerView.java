package views;

import controllers.SellerController;
import models.ProductCategory;

import java.io.IOException;
import java.util.Scanner;

public class SellerView {

    private final SellerController sellerController;

    public SellerView() throws IOException {
        this.sellerController = new SellerController();
    }

    public void start() {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Ajouter un produit");
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            try {
                switch (input) {
                    case "1":
                        this.showProductPrompt(sc);
                        break;
                    case "0":
                        System.exit(0);
                    default:
                        System.out.println("Choix invalide");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!input.equals("exit"));
    }

    public void showProductPrompt(Scanner sc) {
        System.out.println("Veuillez entrer le nom du produit :");
        String name = sc.nextLine();
        System.out.println("Veuillez entrer la description du produit :");
        String description = sc.nextLine();
        System.out.println("Veuillez entrer le prix du produit :");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Veuillez entrer la quantité du produit :");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.println(ProductCategory.categoriesToString());
        int categoryIndex = Integer.parseInt(sc.nextLine());
        if (categoryIndex == 0) {
            start();
        } else if (categoryIndex < 0 || categoryIndex > ProductCategory.values().length) {
            System.out.println("Choix invalide");
        }
        ProductCategory category = ProductCategory.values()[categoryIndex - 1];
        switch (category) {
            case LEARNING_MATERIALS -> showLearningMaterialPrompt(sc, name, description, price, quantity);
        }
    }

    public void showLearningMaterialPrompt(Scanner sc, String name, String description, double price, int quantity) {
        boolean addProduct;
        System.out.println("Veuillez entrer la marque du produit :");
        String brand = sc.nextLine();
        System.out.println("Veuillez entrer le modèle du produit :");
        String model = sc.nextLine();
        System.out.println("Veuillez entrer la sous-catégorie du produit ( : imprimé ou électronique) :");
        String subCategory = sc.nextLine();
        System.out.println("Veuillez entrer l'ISBN du produit :");
        String isbn = sc.nextLine();
        System.out.println("Veuillez entrer l'auteur du produit :");
        String author = sc.nextLine();
        System.out.println("Veuillez entrer l'organisation du produit :");
        String organization = sc.nextLine();
        System.out.println("Veuillez entrer la date de publication du produit :");
        String publicationDate = sc.nextLine();
        System.out.println("Veuillez entrer l'édition du produit :");
        String edition = sc.nextLine();

        addProduct = this.sellerController.addProduct(name, description, price, quantity, brand, model, subCategory, isbn, author, organization, publicationDate, edition);
        if (addProduct) {
            System.out.println("Produit ajouté avec succès");
        } else {
            System.out.println("Erreur lors de l'ajout du produit");
            showProductPrompt(sc);
        }


    }
}