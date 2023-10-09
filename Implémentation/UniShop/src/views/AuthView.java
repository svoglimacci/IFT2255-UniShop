package views;

import controllers.AuthController;

import java.util.Scanner;

public class AuthView {
    private final AuthController authController;

    public AuthView() {
        this.authController = new AuthController();
    }

    public void showLogin() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Veuillez entrer votre nom d'utilisateur : ");
            String username = sc.nextLine();
            System.out.println("Veuillez entrer votre mot de passe : ");
            String password = sc.nextLine();
            boolean login = this.authController.login(username, password);
            if (login) {
                System.out.println("Vous êtes maintenant connecté!");
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe invalide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRegister() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Veuillez entrer votre prénom : ");
            String firstName = sc.nextLine();
            System.out.println("Veuillez entrer votre nom : ");
            String lastName = sc.nextLine();
            System.out.println("Veuillez entrer votre email : ");
            String email = sc.nextLine();
            System.out.println("Veuillez entrer votre nom d'utilisateur : ");
            String username = sc.nextLine();
            System.out.println("Veuillez entrer votre mot de passe : ");
            String password = sc.nextLine();
            boolean register = this.authController.register(firstName, lastName, email, username, password);
            if (register) {
                System.out.println("Vous êtes maintenant inscrit!");
            } else {
                System.out.println("Erreur lors de l'inscription");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
