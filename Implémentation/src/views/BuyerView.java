package views;

import controllers.BuyerController;
import models.Book;
import models.Products;

import java.io.IOException;
import java.util.Scanner;

import static models.BookGenre.*;

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

    public void showCategories(Scanner sc) {
            System.out.println("Veuillez choisir une catégorie :");
            System.out.println("1. Livres et manuels");
            System.out.println("2. Ressources d'apprentissage");
            System.out.println("3. Articles de papeterie");
            System.out.println("4. Équipement de bureau");
            System.out.println("5. Matériel informatique");
            System.out.println("0. Retour");
            String input = sc.nextLine();
            try {
                switch (input) {
                    case "1":
                        this.showBooks(sc);
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


    public void showBooks(Scanner sc) {
            System.out.println("Veuillez choisir une option :");
            System.out.println("1. Manuels académiques");
            System.out.println("2. Romans");
            System.out.println("3. Bandes dessinées");
            System.out.println("4. Documentaires");
            System.out.println("0. Retour");
            String input = sc.nextLine();
            try {
                switch (input) {
                    case "1":
                        this.showAcademicBooks(sc);
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

    public void showAcademicBooks(Scanner sc) {
        Products products = this.buyerController.getBooksByGenre(ACADEMIC.getDisplayName());
        System.out.println("Veuillez choisir un livre :");
        for (Book book : products.getBooks()) {
            System.out.println(book.getName());
        }
        System.out.println("0. Retour");
        String input = sc.nextLine();


    }
}