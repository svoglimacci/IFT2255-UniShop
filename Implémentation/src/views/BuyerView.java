package views;

import controllers.ProductController;
import controllers.UserController;
import models.Buyer;
import models.Product;
import models.ProductCategory;
import models.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BuyerView {
    private final UserController userController;
    private final ProductController productController;
    private final String username;
    private final Buyer user;

    public BuyerView(Buyer user) throws IOException {
        this.user = user;
        this.username = user.getUsername();
        this.userController = new UserController();
        this.productController = new ProductController();
    }

public void start() {
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Afficher les produits");
        System.out.println("2. Modifier le profil");
        System.out.println("0. Se déconnecter");

        try {
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    showCategories(sc);
                    break;
                case "2":
                    modifyProfile(sc);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        } catch (Exception e) {
            System.out.println("Choix invalide");
        }
    }
}

public void showCategories(Scanner sc) {
    ProductCategory[] categories = ProductCategory.values();

    System.out.println("Veuillez choisir une catégorie :");
    System.out.println(ProductCategory.categoriesToString());
    System.out.println("0. Retour");

    while (true) {
        try {
            String input = sc.nextLine();
            int categoryIndex = Integer.parseInt(input);

            if (categoryIndex >= 0 && categoryIndex <= categories.length) {
                if (categoryIndex == 0) {
                    this.start();
                } else {
                    ProductCategory category = categories[categoryIndex - 1];
                    showProducts(sc, category);
                }
                return; // Exit the loop after a valid choice
            } else {
                System.out.println("Choix invalide");
            }
        } catch (NumberFormatException e) {
            System.out.println("Choix invalide");
        }
    }
}


public void showProducts(Scanner sc, ProductCategory category) {
    List<? extends Product> products = this.productController.getProductsByCategory(category);

    if (products.isEmpty()) {
        System.out.println("Aucun produit dans cette catégorie");
        this.showCategories(sc);
        return;
    }

    int idx = 1;
    System.out.println("Veuillez choisir un produit :");
    System.out.println("0. Retour");

    for (Product product : products) {
        System.out.println(product.productToString());
        idx++;
    }

    while (true) {
        try {
            String input = sc.nextLine();
            int productChoice = Integer.parseInt(input);

            if (productChoice >= 0 && productChoice < idx) {
                if (productChoice == 0) {
                    this.showCategories(sc);
                } else {
                    this.showProduct(products.get(productChoice - 1), sc, category);
                }
                this.showCategories(sc);
                return;
            } else {
                System.out.println("Choix invalide");
            }
        } catch (NumberFormatException e) {
            System.out.println("Choix invalide");
        }
    }
}

    private void showProduct(Product product, Scanner sc, ProductCategory category) {
    User user = this.user;
    boolean isLiked = user.getLikes().contains(product.getId());
    boolean success1, success2;

    System.out.println(product.productDetailsToString());
    System.out.println("Veuillez choisir une option :");

    if (isLiked) {
        System.out.println("1. Retirer la mention j'aime");
    } else {
        System.out.println("1. Ajouter une mention j'aime");
    }

    System.out.println("0. Retour");

    while (true) {
        try {
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    if (isLiked) {
                         success1 = this.productController.changeAttribute(product, "likes", "remove", category);
                         success2 = this.userController.removeLike(this.user, product);
                    } else {
                        success1 = this.productController.changeAttribute(product, "likes", "add", category);
                        success2 = this.userController.addLike(this.user, product);
                    }
                    if (success1 && success2) {
                        System.out.println(isLiked ? "Mention j'aime retirée avec succès" : "Mention j'aime ajoutée avec succès");
                    } else {
                        System.out.println("Erreur lors de l'opération");
                    }

                    this.showProduct(product, sc, category);
                    return;

                case "0":
                    this.showProducts(sc, category);
                    return;

                default:
                    System.out.println("Choix invalide");
            }
        } catch (Exception e) {
            System.out.println("Choix invalide");
        }
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

    while (true) {
        try {
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    modifyProperty("firstName", "prénom", sc);
                    break;
                case "2":
                    modifyProperty("lastName", "nom", sc);
                    break;
                case "3":
                    modifyProperty("address", "adresse", sc);
                    break;
                case "4":
                    modifyProperty("phoneNumber", "numéro de téléphone", sc);
                    break;
                case "5":
                    modifyProperty("password", "mot de passe", sc);
                    break;
                case "0":
                    this.start();
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        } catch (Exception e) {
            System.out.println("Choix invalide");
        }
    }
}

private void modifyProperty(String property, String propertyName, Scanner sc) {
    System.out.println("Veuillez entrer le nouveau " + propertyName + " :");
    String value = sc.nextLine();
    boolean modify = this.userController.changeProperty(property, value, this.username, false);

    if (modify) {
        System.out.println("Modification effectuée avec succès");
        this.start();
    } else {
        System.out.println("Erreur lors de la modification");
        modifyProfile(sc);
    }
}
}