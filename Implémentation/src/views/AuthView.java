package views;

import controllers.ProductController;
import controllers.UserController;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthView {
    private final UserController userController;


    public AuthView() throws IOException {
        this.userController = new UserController();

    }

    public void start() {
        System.out.println("Bienvenue sur UniShop!");
        System.out.println("1. Se connecter");
        System.out.println("2. Créer un compte");
        System.out.println("0. Quitter");
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            try {
                switch (input) {
                    case "1":
                        this.showLoginMenu(sc);
                        break;
                    case "2":
                        this.showRegisterMenu(sc);
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix invalide");
                        break;
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        } while (!input.equals("exit"));
    }

    public void showRegisterMenu(Scanner sc) {
        System.out.println("Souhaitez-vous créer un compte : ");
        System.out.println("1. Acheteur");
        System.out.println("2. Vendeur");
        System.out.println("0. Retour");
        String input = sc.nextLine();
        try {
            switch (input) {
                case "1":
                    this.showRegisterPrompt(sc, false);
                    break;
                case "2":
                    this.showRegisterPrompt(sc, true);
                    break;
                case "0":
                    this.start();
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRegisterPrompt(Scanner sc, boolean isSeller) throws IOException {
        boolean register;
        String username;
        String email;
        String password;
        String address;
        String businessName;
        String firstName;
        String lastName;
        String phoneNumber;

        do {
            System.out.println("Veuillez entrer une adresse courriel: ");
            email = sc.nextLine();
        } while (!this.userController.validateEmail(email));

        do {
            System.out.println("Veuillez entrer un nom d'utilisateur: ");
            username = sc.nextLine();

        } while (!this.userController.validateUsername(username, isSeller));


        do {
            System.out.println("Veuillez entrer un mot de passe: ");
            password = sc.nextLine();
        } while (!this.userController.validatePassword(password));


        do {
            System.out.println("Veuillez entrer votre adresse: ");
            address = sc.nextLine();
        } while (!this.userController.validateAddress(address));

        do {
            System.out.println("Veuillez entrer un numéro de téléphone: ");
            phoneNumber = sc.nextLine();
        } while (!this.userController.validatePhoneNumber(phoneNumber));

        if (isSeller) {
            do {
                System.out.println("Veuillez entrer le nom d'entreprise: ");
                businessName = sc.nextLine();
            } while (!this.userController.validateName(businessName));
            register = this.userController.register(businessName, email, username, password, address, phoneNumber);


        } else {
            do {
                System.out.println("Veuillez entrer un prénom: ");
                firstName = sc.nextLine();
                System.out.println("Veuillez entrer un nom de famille: ");
                lastName = sc.nextLine();
            } while (!this.userController.validateName(firstName, lastName));
            register = this.userController.register(firstName, lastName, email, username, password, address, phoneNumber);
        }


        if (register) {
            System.out.println("Vous êtes maintenant enregistré!");
            if (isSeller) {
                System.out.println("Veuillez vous connecter et ajouter un produit à votre inventaire afin de confirmer votre inscription.");
            }
            this.start();

        } else {
            System.out.println("Erreur lors de la création du compte.\n");
            this.showLoginPrompt(sc, isSeller);

        }
    }

    public void showLoginMenu(Scanner sc) {

        System.out.println("Souhaitez-vous vous connecter en tant que: ");
        System.out.println("1. Acheteur");
        System.out.println("2. Vendeur");
        System.out.println("0. Retour");
        String input = sc.nextLine();
        try {
            switch (input) {
                case "1":
                    this.showLoginPrompt(sc, false);
                    break;
                case "2":
                    this.showLoginPrompt(sc, true);
                    break;
                case "0":
                    this.start();
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoginPrompt(Scanner sc, boolean isSeller) throws IOException {
        boolean login;
        System.out.println("Veuillez entrer un nom d'utilisateur : ");
        String username = sc.nextLine();
        System.out.println("Veuillez entrer un mot de passe : ");
        String password = sc.nextLine();

        login = this.userController.login(username, password, isSeller);
        if (login) {
            System.out.println("Vous êtes maintenant connecté!");
            if (isSeller) {
                SellerView sellerView = new SellerView(username);
                sellerView.start();
            } else {
                BuyerView buyerView = new BuyerView(username);
                buyerView.start();
            }

        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.\n");
            this.showLoginPrompt(sc, isSeller);

        }
    }

}