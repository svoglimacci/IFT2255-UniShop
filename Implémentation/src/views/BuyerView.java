package views;

import controllers.BuyerController;
import models.Product;
import models.ProductCategory;

import java.io.IOException;
import java.util.Scanner;

public class BuyerView {

    private final BuyerController buyerController;

    public BuyerView() throws IOException {
        this.buyerController = new BuyerController();
    }

    public void start() {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Afficher les produits");
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
                        System.exit(0);
                    default:
                        System.out.println("Choix invalide");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!input.equals("exit"));
    }

    public void showCategories(Scanner sc) {
        System.out.println(ProductCategory.categoriesToString());
        int categoryIndex = Integer.parseInt(sc.nextLine());
        if (categoryIndex == 0) {
            start();
        } else if (categoryIndex < 0 || categoryIndex > ProductCategory.values().length) {
            System.out.println("Choix invalide");
        }
        ProductCategory category = ProductCategory.values()[categoryIndex - 1];
        showProducts(sc, category);
    }

    public void showProducts(Scanner sc, ProductCategory category) {
        int idx = 1;
        Iterable<? extends Product> products = this.buyerController.getProductsByCategory(category);
        System.out.println("Veuillez choisir un produit :");
        for (Product product : products) {
            System.out.println(product.productToString());
            idx++;
        }
        String input = sc.nextLine();
        System.out.println("0. Retour");
        try {
            switch (input) {
                case "0":
                    showCategories(sc);
                    break;
                default:
                    int productChoice = Integer.parseInt(input);
                    if (productChoice > 0 && productChoice < idx) {
                        this.showProduct(products.iterator().next());
                    } else {
                        System.out.println("Choix invalide");
                    }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    private void showProduct(Product product) {
        System.out.println(product.productDetailsToString());
    }
}