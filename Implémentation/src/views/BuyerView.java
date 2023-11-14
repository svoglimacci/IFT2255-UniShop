package views;

import controllers.ProductController;
import controllers.UserController;
import models.*;

import java.io.IOException;

import java.time.LocalDate;
import java.util.*;

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
                System.out.println("3. Voir le panier d'achat");
                System.out.println("4. Passer une commande");
                System.out.println("5. Rechercher un produit");
                System.out.println("6. Mes achats");
                System.out.println("7. Trouver un revendeur");
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
                    case "6" -> showPurchases(sc);
                    case "7" -> searchSeller(sc);
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

    private void searchSeller(Scanner sc) {
        System.out.println("Recherche de revendeurs :");
        System.out.print("Entrez un mot-clé de recherche : ");
        String input;
        String keyword = sc.nextLine();

        List<Seller> searchResults = userController.searchSellers(keyword);

        if (searchResults.isEmpty()) {
            System.out.println("Aucun vendeur trouvé pour la recherche spécifiée.");
        } else {
            while (true) {
                System.out.println("Résultats de la recherche :");
                int idx = 2;
                for (Seller seller : searchResults) {
                    System.out.println(idx + ". " + seller.toString());
                    idx++;
                }

                System.out.println("Veuillez choisir une option :");
                System.out.println("1. Filtrer les résultats");
                System.out.println("#. Entrer le numéro du vendeur pour voir les détails");
                System.out.println("0. Retour");

                try {
                    input = sc.nextLine();
                    if (input.equals("0")) {
                        return;
                    } else if (input.equals("1")) {
                        searchResults = filterBuyers(sc, searchResults);
                    } else {
                        int sellerChoice = Integer.parseInt(input) - 2;
                        if (sellerChoice >= 0 && sellerChoice < searchResults.size()) {
                            System.out.println("...Show Seller infos...");
                            return;
                        } else {
                            System.out.println("Choix invalide");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Choix invalide");
                }
            }
        }
    }

    private void showPurchases(Scanner sc) {
        List<Product> purchases = new ArrayList<>();
        for (UUID id : user.getPurchases()) {
            purchases.add(productController.getProductById(id));
        }
        String input;
        if (purchases.isEmpty()) {
            System.out.println("Aucun achat effectué");
            return;
        }

        int idx = 1;
        System.out.println("Veuillez choisir une option :");
        System.out.println("#. Entrer le numéro de la commande pour voir les détails");
        System.out.println("0. Retour");

        for (Order order : user.getOrders()) {
            System.out.println(idx + ". " + order.getId() + " | " + order.getStatus());

            idx++;
        }

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
                    System.out.println("Veuillez choisir une option :");
                    System.out.println("1. Effectuer un retour ou échange");
                    System.out.println("2. Annuler la commande");
                    System.out.println("0. Retour");
                    input = sc.nextLine();
                    if (!input.isEmpty()) {
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


    private List<Seller> filterBuyers(Scanner sc, List<Seller> searchResults) {
        List<Seller> filteredResults = new ArrayList<>(searchResults);
        String input;
        try {
            System.out.println("Veuillez choisir une option :");
            System.out.println("1. Filtrer par nom");
            System.out.println("2. Filtrer par adresse");
            System.out.println("3. Filtrer par catégories de produits");
            System.out.println("0. Retour");
            input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    return userController.sortSellers(filteredResults, "name");
                }
                case "2" -> {
                    return userController.sortSellers(filteredResults, "address");
                }
                case "3" -> {
                    System.out.println("Veuillez choisir une catégorie :");
                    System.out.println("1. Livres et Manuels");
                    System.out.println("2. Matériel Informatique");
                    System.out.println("3. Ressources d'apprentissage");
                    System.out.println("4. Articles de papeterie");
                    System.out.println("5. Équipêment de bureau");
                    System.out.println("0. Retour");
                    input = sc.nextLine();
                    switch (input) {
                        case "1" -> {
                            return userController.filterSellers(filteredResults, "Books");
                        }
                        case "2" -> {
                            return userController.filterSellers(filteredResults, "Electronics");
                        }
                        case "3" -> {
                            return userController.filterSellers(filteredResults, "LearningMaterials");
                        }
                        case "4" -> {
                            return userController.filterSellers(filteredResults, "OfficeSupplies");
                        }
                        case "5" -> {
                            return userController.filterSellers(filteredResults, "OfficeEquipment");
                        }
                        case "0" -> {
                            return filteredResults;
                        }
                        default -> {
                            System.out.println("Choix invalide");
                        }
                    }
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

        if (user.getCart().getItems().isEmpty()) {
            System.out.println("Aucun produit dans le panier");
            return;
        }

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

        Set<UUID> products = new HashSet<>();

        boolean orderPlaced = true;
        if (orderPlaced) {
            System.out.println("Commande passée avec succès! Numéro de commande : " + orderID);
            for (CartItem product : user.getCart().getItems()) {
                user.addPurchase(product.getId());

                products.add(product.getId());

            }
            Order order = new Order(orderID, user.getId(), user.getId(), products, user.getCart().calculateTotalPrice(), "En production");
            user.addOrder(order);

            user.getCart().getItems().clear();
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
                if (cart.getItems().isEmpty()) {
                    System.out.println("Aucun produit dans le panier");
                    return;
                }
                int idx = 2;
                for (CartItem product : cart.getItems()) {
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

                if (input.equals("0")) {
                    return;
                }
                if (input.equals("1")) {
                    reviewOrder(sc);
                    return;
                }
                int choice = Integer.parseInt(input) - 1;
                if (choice >= 0 && choice <= cart.getItems().size()) {
                    if (choice != 0) {
                        System.out.println("Veuillez entrer la nouvelle quantité :");
                        int quantity = Integer.parseInt(sc.nextLine());
                        if (quantity < 0) {
                            System.out.println("Quantité invalide");
                            return;
                        }
                        CartItem product = (CartItem) cart.getItems().toArray()[choice - 1];
                        if (quantity == 0) {
                            cart.getItems().remove(product);
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

                    case "1" -> {
                        showProducts(sc, "Books");
                        return;
                    }
                    case "2" -> {
                        showProducts(sc, "Electronics");
                        return;
                    }
                    case "3" -> {
                        showProducts(sc, "LearningMaterials");
                        return;
                    }
                    case "4" -> {
                        showProducts(sc, "OfficeSupplies");
                        return;
                    }
                    case "5" -> {
                        showProducts(sc, "OfficeEquipment");
                        return;
                    }

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

            System.out.println(idx + ". " + product.productToString());

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