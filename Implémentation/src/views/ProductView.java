package views;

import controllers.ProductController;
import controllers.UserController;
import models.Buyer;
import models.Product;
import models.User;

import java.io.IOException;
import java.util.Scanner;

public class ProductView {
    private final UserController userController;
    private final ProductController productController;
    private final User user;
    private final Product product;

    public ProductView(User user, Product product) throws IOException {
        this.userController = new UserController();
        this.productController = new ProductController();
        this.user = user;
        this.product = product;
    }

    public void start(Scanner sc) {
        String input;
        boolean success1, success2;
        boolean displayMenu = true;


        while (true) {
            boolean isLiked = user.getLikes().contains(product.getId());
            if (displayMenu) {
                System.out.println(product.productDetailsToString());
                System.out.println("Veuillez choisir une option :");
                System.out.println("1. Ajouter au panier");
                if (isLiked) {
                    System.out.println("2. Retirer la mention j'aime");
                } else {
                    System.out.println("2. Ajouter une mention j'aime");
                }
                System.out.println("0. Retour");
            }
            try {
                input = sc.nextLine();

                switch (input) {
                    case "1" -> {
                        System.out.println("Veuillez entrer la quantité :");
                        int quantity = Integer.parseInt(sc.nextLine());
                        userController.addProductToCart((Buyer) user, product, quantity);
                    }
                    case "2" -> {
                        if (isLiked) {
                            success1 = productController.changeAttribute(product, "likes", "remove");
                            success2 = userController.removeLike((Buyer) user, product);
                        } else {
                            success1 = productController.changeAttribute(product, "likes", "add");
                            success2 = userController.addLike((Buyer) user, product);
                        }
                        if (success1 && success2) {
                            System.out.println(isLiked ? "Mention j'aime retirée avec succès" : "Mention j'aime ajoutée avec succès");
                        } else {
                            System.out.println("Erreur lors de l'opération");
                        }
                    }
                    case "0" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Choix invalide");
                        displayMenu = false;
                    }
                }
            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
        }
    }
}