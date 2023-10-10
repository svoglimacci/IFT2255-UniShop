package views;

import controllers.AuthController;

import java.io.IOException;
import java.util.Scanner;

public class AuthView {
    private final AuthController authController;

    public AuthView() throws IOException {
        this.authController = new AuthController();
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
            } catch (Exception e) {
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
                    return;
                default:
                    System.out.println("Choix invalide");
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRegisterPrompt(Scanner sc, boolean isSeller) {
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
            System.out.println("Veuillez entrer votre adresse courriel valide : ");
            email = sc.nextLine();
        } while (!this.authController.validateEmail(email));

        do {
            System.out.println("Veuillez entrer votre nom d'utilisateur valide: ");
            username = sc.nextLine();

        } while (!this.authController.validateUsername(username, isSeller));


        do {
            System.out.println("Veuillez entrer votre mot de passe valide : ");
            password = sc.nextLine();
        } while (!this.authController.validatePassword(password));


        do {
            System.out.println("Veuillez entrer une adresse valide : ");
            address = sc.nextLine();
        } while (!this.authController.validateAddress(address));

        do {
            System.out.println("Veuillez entrer un numéro de téléphone valide : ");
            phoneNumber = sc.nextLine();
        } while (!this.authController.validatePhoneNumber(phoneNumber));

        if (isSeller) {
            do {
                System.out.println("Veuillez entrer un nom d'entreprise valide : ");
                businessName = sc.nextLine();
            } while (!this.authController.validateName(businessName));
            register = this.authController.register(businessName, email, username, password, address, phoneNumber);
        } else {
            do {
                System.out.println("Veuillez entrer un prénom valide : ");
                firstName = sc.nextLine();
                System.out.println("Veuillez entrer un nom de famille valide : ");
                lastName = sc.nextLine();
            } while (!this.authController.validateName(firstName, lastName));
            register = this.authController.register(firstName, lastName, email, username, password, address, phoneNumber);
        }

        if (register) {
            System.out.println("Vous êtes maintenant enregistré!");

        } else {
            System.out.println("Erreur lors de la création du compte.\n");
            this.showLoginPrompt(sc, isSeller);

        }


    }

    public void showLoginMenu(Scanner sc) {

        System.out.println("Souhaitez-vous vous connecter en tant que : ");
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
                    return;
                default:
                    System.out.println("Choix invalide");
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoginPrompt(Scanner sc, boolean isSeller) {
        boolean login;
        System.out.println("Veuillez entrer votre nom d'utilisateur : ");
        String username = sc.nextLine();
        System.out.println("Veuillez entrer votre mot de passe : ");
        String password = sc.nextLine();

        login = this.authController.login(username, password, isSeller);
        if (login) {
            System.out.println("Vous êtes maintenant connecté!");

        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.+\n");
            this.showLoginPrompt(sc, isSeller);

        }
    }

}
