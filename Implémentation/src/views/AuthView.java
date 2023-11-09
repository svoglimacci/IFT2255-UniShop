package views;

import controllers.UserController;
import models.Buyer;
import models.Seller;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Predicate;
public class AuthView {
    private final UserController userController;

    public AuthView() throws IOException {
        this.userController = new UserController();
    }

public void start() {
   Scanner sc = new Scanner(System.in);
   String input;
   boolean displayMenu = true;
   while (true) {
       if (displayMenu) {
           System.out.println("Bienvenue sur UniShop!");
           System.out.println("1. Se connecter");
           System.out.println("2. Créer un compte");
           System.out.println("0. Quitter");
       }

       try {
           input = sc.nextLine();
           switch (input) {
               case "1" -> showLoginMenu(sc);
               case "2" -> showRegisterMenu(sc);
               case "0" -> {
                   System.exit(0);
               }
               default -> {
                  displayMenu = false;
                  System.out.println("Choix invalide");
               }
           }
       } catch (Exception e) {
           System.out.println("Veuillez entrer un nombre");
       }
   }
}


public void showRegisterMenu(Scanner sc) {
   String input;
   while (true) {
       System.out.println("Souhaitez-vous créer un compte : ");
       System.out.println("1. Acheteur");
       System.out.println("2. Vendeur");
       System.out.println("0. Retour");

       input = sc.nextLine();

       switch (input) {
           case "1" -> showRegisterPrompt(sc, false);
           case "2" -> showRegisterPrompt(sc, true);
           case "0" -> {
               return;
           }
           default -> System.out.println("Choix invalide");
       }
   }
}

    public void showRegisterPrompt(Scanner sc, boolean isSeller) {
    String email = getUserInput(sc, "Veuillez entrer une adresse courriel: ", userController::validateEmail);
    String username = getUserInput(sc, "Veuillez entrer un nom d'utilisateur: ", input -> userController.validateUsername(input, isSeller));
    String password = getUserInput(sc, "Veuillez entrer un mot de passe: ", userController::validatePassword);
    String address = getUserInput(sc, "Veuillez entrer votre adresse: ", userController::validateAddress);
    String phoneNumber = getUserInput(sc, "Veuillez entrer un numéro de téléphone: ", userController::validatePhoneNumber);
    boolean register;

    if (isSeller) {
        String businessName = getUserInput(sc, "Veuillez entrer le nom d'entreprise: ", userController::validateName);
        register = userController.register(businessName, email, username, password, address, phoneNumber);
    } else {
        String firstName = getUserInput(sc, "Veuillez entrer un prénom: ", userController::validateName);
        String lastName = getUserInput(sc, "Veuillez entrer un nom de famille: ", userController::validateName);
        register = userController.register(firstName, lastName, email, username, password, address, phoneNumber);
    }

    if (register) {
        System.out.println("Vous êtes maintenant enregistré!");
        if (isSeller) {
            System.out.println("Veuillez vous connecter et ajouter un produit à votre inventaire afin de confirmer votre inscription.");
        }
    } else {
        System.out.println("Erreur lors de la création du compte.\n");
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


    public void showLoginMenu(Scanner sc) {
        boolean displayMenu = true;
        String input;

    while (true) {
        if (displayMenu) {
            System.out.println("Souhaitez-vous vous connecter en tant que :");
            System.out.println("1. Acheteur");
            System.out.println("2. Vendeur");
            System.out.println("0. Retour");
        }
        try {
            input = sc.nextLine();

            switch (input) {
                case "1":
                    showLoginPrompt(sc, false);
                    return;
                case "2":
                    showLoginPrompt(sc, true);
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Choix invalide");
                    displayMenu = false;
            }
        } catch (Exception e) {
            System.out.println("Veillez entrer un nombre");
            displayMenu = false;
        }
    }
}

    public void showLoginPrompt(Scanner sc, boolean isSeller) throws IOException {
    Object user;

    while (true) {
        System.out.println("Veuillez entrer un nom d'utilisateur : ");
        String username = sc.nextLine();
        System.out.println("Veuillez entrer un mot de passe : ");
        String password = sc.nextLine();

        user = userController.login(username, password, isSeller);

        if (user != null) {
            System.out.println("Vous êtes maintenant connecté!");
            if (isSeller) {
                Seller seller = (Seller) user;
                SellerView sellerView = new SellerView(seller);
                sellerView.start();
            } else {
                Buyer buyer = (Buyer) user;
                BuyerView buyerView = new BuyerView(buyer);
                buyerView.start();
            }
            return;
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.\n");
            return;
        }
    }
}

}