package views;

import controllers.BuyerController;
import models.Product;
import models.ProductCategory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BuyerView {

    private final BuyerController buyerController;

    public BuyerView() throws IOException {
        this.buyerController = new BuyerController();
    }

    public void start() {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Afficher les produits");
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
        List<? extends Product> products = this.buyerController.getProductsByCategory(category);
        if (products.isEmpty()) {
            System.out.println("Aucun produit dans cette catégorie");
            this.showCategories(sc);
        }
        else {
            System.out.println("Veuillez choisir un produit :");
            System.out.println("0. Retour");
            for (Product product : products) {
                System.out.println(product.productToString());
                idx++;
            }
            String input = sc.nextLine();
            try {
                switch (input) {
                    case "0":
                        this.showCategories(sc);
                        break;
                    default:
                        int productChoice = Integer.parseInt(input);
                        if (productChoice > 0 && productChoice < idx) {
                            this.showProduct(products.iterator().next());
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

    private void showProduct(Product product) {
        System.out.println(product.productDetailsToString());
    }
}