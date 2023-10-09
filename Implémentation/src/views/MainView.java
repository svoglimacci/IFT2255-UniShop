package views;

import java.util.Scanner;

public class MainView {

    public void start() {
        AuthView authView = new AuthView();
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
                        authView.showLogin();
                        break;
                    case "2":
                        authView.showRegister();
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
        } while (!input.equals("0"));

    }

}