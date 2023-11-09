package views;

import controllers.ProductController;
import controllers.UserController;
import models.Buyer;
import models.CartItem;
import models.Product;
import models.ShoppingCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;

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
        String input;
        boolean displayMenu = true;

        while (true) {
            if (displayMenu) {
                System.out.println("Veuillez choisir une option :");
                System.out.println("1. Afficher les produits");
                System.out.println("2. Modifier le profil");
                System.out.println("3. Voir le panier");
                System.out.println("4. Passer une commande");
                System.out.println("5. Rechercher un produit");
                System.out.println("0. Se déconnecter");
            }

            try {
                input = sc.nextLine();
                switch (input) {
                    case "1" -> showCategories(sc);
                    case "2" -> modifyProfile(sc);
                    case "3" -> showCart(sc);
                    case "4" -> reviewOrder(sc);
                    case "5" -> searchProduct(sc);
                    case "0" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Choix invalide");
                        displayMenu = false;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void searchProduct(Scanner sc) {
        System.out.println("Recherche de produits :");
        System.out.print("Entrez un mot-clé de recherche : ");
        String input;
        String keyword = sc.nextLine();

        List<Product> searchResults = productController.searchProducts(keyword);

        if (searchResults.isEmpty()) {
            System.out.println("Aucun produit trouvé pour la recherche spécifiée.");
        } else {
            System.out.println("Résultats de la recherche :");
            int idx = 2;
            for (Product product : searchResults) {
                System.out.println(idx + ". " + product.productToString());
                idx++;
            }
            while (true) {
                System.out.println("Veuillez choisir une option :");
                System.out.println("1. Filtrer les résultats");
                System.out.println("#. Entrer le numéro du produit pour voir les détails");
                System.out.println("0. Retour");
                try {
                    input = sc.nextLine();
                    if (input.equals("0")) {
                        return;
                    }
                    if (input.equals("1")) {

                        searchResults = filterResults(sc, searchResults);
                        System.out.println("Veuillez choisir une option :");
                        System.out.println("1. Filtrer les résultats");
                        System.out.println("#. Entrer le numéro du produit pour voir les détails");
                        System.out.println("0. Retour");
                        System.out.println("Résultats de la recherche :");
                        idx = 2;
                        for (Product product : searchResults) {
                            System.out.println(idx + ". " + product.productToString());
                            idx++;
                        }
                    }
                    int productChoice = Integer.parseInt(input) - 2;
                    if (productChoice >= 0 && productChoice < searchResults.size()) {

                        ProductView productView = new ProductView(user, searchResults.get(productChoice));
                        productView.start(sc);
                        return;
                    } else {
                        System.out.println("Choix invalide");
                    }
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Choix invalide");
                }
            }

        }
    }

    private List<Product> filterResults(Scanner sc, List<Product> searchResults) {
        List<Product> filteredResults = new ArrayList<>(searchResults);
        String input;
        try {
            System.out.println("Veuillez choisir une option :");
            System.out.println("1. Filtrer par catégorie");
            System.out.println("2. Filtrer par prix");
            System.out.println("3. Filtrer par nom");
            System.out.println("4. Filtrer par note moyenne");
            System.out.println("5. Filtrer par popularité");
            System.out.println("6. Filtrer par promotion");
            System.out.println("0. Retour");
            input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    return productController.sortProducts(filteredResults, "category");
                }
                case "2" -> {
                    return productController.sortProducts(filteredResults, "price");
                }
                case "3" -> {
                    return productController.sortProducts(filteredResults, "name");
                }
                case "4" -> {
                    return productController.sortProducts(filteredResults, "rating");
                }
                case "5" -> {
                    return productController.sortProducts(filteredResults, "likes");
                }
                case "6" -> {
                    return productController.sortProducts(filteredResults, "isPromoted");
                }
                case "0" -> {
                    return filteredResults;
                }
                default -> {
                    System.out.println("Choix invalide");
                }
            }
        } catch (Exception e) {
            System.out.println("Choix invalide");
        }
        return filteredResults;
    }

    public void reviewOrder(Scanner sc) {

        System.out.println("Utiliser l'adresse du profil pour la livraison ? (Oui/Non) :");
        String useProfileAddressChoice = sc.nextLine().toLowerCase();
        String deliveryAddress;

        if (useProfileAddressChoice.equals("oui")) {
            deliveryAddress = user.getAddress();
        } else {
            deliveryAddress = getUserInput(sc, "Nouvelle adresse de livraison : ", userController::validateAddress);
        }
        System.out.println("Veuillez fournir les informations suivantes :");
        String phoneNumber = getUserInput(sc, "Numéro de téléphone : ", userController::validatePhoneNumber);
        UUID orderID = UUID.randomUUID();
        boolean orderPlaced = true;
        if (orderPlaced) {
            System.out.println("Commande passée avec succès! Numéro de commande : " + orderID);
            user.getCart().getProducts().clear();
        } else {
            System.out.println("Erreur lors de la commande. Veuillez réessayer.");
        }
    }

    private String getUserInput(Scanner sc, String prompt, Predicate<String> validator) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine();
        } while (!validator.test(input));
        return input;
    }


    public void showCart(Scanner sc) {
        boolean displayMenu = true;
        String input;
        while (true) {
            ShoppingCart cart = user.getCart();
            if (displayMenu) {
                System.out.println("Produits dans le panier :");
                if (cart.getProducts().isEmpty()) {
                    System.out.println("Aucun produit dans le panier");
                    return;
                }
                int idx = 2;
                for (CartItem product : cart.getProducts()) {
                    System.out.println(idx + ". " + product.productToString());
                    idx++;
                }
                System.out.println("Coût total : " + cart.calculateTotalPrice() + "$\n");
                System.out.println("Veuillez choisir une option :");
                System.out.println("1. Passer la commande");
                System.out.println("#. Entrer le numéro du produit pour modifier sa quantité");
                System.out.println("0. Retour");
            }
            try {
                input = sc.nextLine();
                //TODO: finish this.
                if (input.equals("0")) {
                    return;
                }
                if (input.equals("1")) {
                    reviewOrder(sc);
                    return;
                }
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice <= cart.getProducts().size()) {
                    if (choice != 0) {
                        System.out.println("Veuillez entrer la nouvelle quantité :");
                        int quantity = Integer.parseInt(sc.nextLine());
                        if (quantity < 0) {
                            System.out.println("Quantité invalide");
                            return;
                        }
                        CartItem product = (CartItem) cart.getProducts().toArray()[choice - 1];
                        if (quantity == 0) {
                            cart.getProducts().remove(product);
                            System.out.println("Produit retiré avec succès\n");
                            return;
                        }
                        product.setQuantity(quantity);
                        System.out.println("Quantité modifiée avec succès\n");
                    }
                    return;
                } else {
                    System.out.println("Choix invalide");
                    displayMenu = false;
                }
            } catch (Exception e) {
                System.out.println("Choix invalide");
            }
        }
    }

    public void showCategories(Scanner sc) {
        boolean displayMenu = true;
        String input;
        while (true) {
            if (displayMenu) {
                System.out.println("Veuillez choisir une catégorie :");
                System.out.println("1. Livres et Manuels");
                System.out.println("2. Matériel Informatique");
                System.out.println("3. Ressources d'apprentissage");
                System.out.println("4. Articles de papeterie");
                System.out.println("5. Équipêment de bureau");
                System.out.println("0. Retour");
            }
            try {
                input = sc.nextLine();
                switch (input) {
                    case "1" -> showProducts(sc, "Books");
                    case "2" -> showProducts(sc, "Electronics");
                    case "3" -> showProducts(sc, "LearningMaterials");
                    case "4" -> showProducts(sc, "OfficeSupplies");
                    case "5" -> showProducts(sc, "OfficeEquipment");
                    case "0" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Choix invalide");
                        displayMenu = false;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Choix invalide");
            }
        }
    }


    public void showProducts(Scanner sc, String category) {
        List<? extends Product> products = productController.getProductsByCategory(category);
        String input;
        if (products.isEmpty()) {
            System.out.println("Aucun produit dans cette catégorie");
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
                input = sc.nextLine();
                int productChoice = Integer.parseInt(input);
                if (input.equals("0")) {
                    return;
                }
                if (productChoice >= 0 && productChoice < idx) {
                    ProductView productView = new ProductView(user, products.get(productChoice - 1));
                    productView.start(sc);
                    return;
                } else {
                    System.out.println("Choix invalide");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Choix invalide");
            }
        }
    }

    //TODO: getUserInput() + validator
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
                    case "1" -> {
                        modifyProperty("firstName", "prénom", sc);
                        return;
                    }
                    case "2" -> {
                        modifyProperty("lastName", "nom", sc);
                        return;
                    }
                    case "3" -> {
                        modifyProperty("address", "adresse", sc);
                        return;
                    }
                    case "4" -> {
                        modifyProperty("phoneNumber", "numéro de téléphone", sc);
                        return;
                    }
                    case "5" -> modifyProperty("password", "mot de passe", sc);
                    case "0", "exit" -> {
                        return;
                    }
                    default -> System.out.println("Choix invalide");
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
        } else {
            System.out.println("Erreur lors de la modification");
        }
    }
}