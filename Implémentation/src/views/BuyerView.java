package views;

import controllers.BuyerController;
import models.*;

import java.io.IOException;
import java.util.Scanner;

import static models.ProductCategory.*;

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
                    default:
                        System.out.println("Choix invalide");
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
                    this.showProducts(sc, BOOKS);
                    break;
                case "2":
                    this.showProducts(sc, LEARNING_MATERIALS);
                    break;
                case "3":
                    this.showProducts(sc, OFFICE_SUPPLIES);
                    break;
                case "4":
                    this.showProducts(sc, OFFICE_FURNITURES);
                    break;
                case "5":
                    this.showProducts(sc, ELECTRONICS);
                    break;
                case "0":
                    start();
                default:
                    System.out.println("Choix invalide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProducts(Scanner sc, ProductCategory category) {
        int idx = 1;
        Iterable<? extends Product> products = switch (category) {
            case BOOKS -> this.buyerController.getBooks();
            case LEARNING_MATERIALS -> this.buyerController.getLearningMaterials();
            case OFFICE_SUPPLIES -> this.buyerController.getOfficeSupplies();
            case OFFICE_FURNITURES -> this.buyerController.getOfficeFurnitures();
            case ELECTRONICS -> this.buyerController.getElectronics();
        };
        System.out.println("Veuillez choisir un produit :");
        for (Product product : products) {
            System.out.println(idx + ". " + product.getName() + " - " + product.getPrice() + "$" + " - " + product.getRating() + "/5" + " - " + product.getReviews().size() + " avis");
            idx++;
        }
        String input = sc.nextLine();
        System.out.println("0. Retour");
        try {
            switch (input) {
                case "0":
                    showCategories(sc);
                    break;
                default:
                    int productChoice = Integer.parseInt(input);
                    if (productChoice > 0 && productChoice < idx) {
                        this.showProduct(products.iterator().next());
                    } else {
                        System.out.println("Choix invalide");
                    }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    private void showProduct(Product product) {
        System.out.println(product.getName());
        System.out.println(product.getDescription());
        System.out.println(product.getPrice() + "$");
        System.out.println(product.getRating() + "/5");

        for (Review review : product.getReviews()) {
            System.out.println(review.toString());
        }


        if (product.getClass().equals(Book.class)) {
            Book book = (Book) product;
            System.out.println(book);
        } else if (product.getClass().equals(LearningMaterial.class)) {
            LearningMaterial learningMaterial = (LearningMaterial) product;
            System.out.println(learningMaterial);
        } else if (product.getClass().equals(OfficeSupply.class)) {
            OfficeSupply officeSupply = (OfficeSupply) product;
            System.out.println(officeSupply);
        } else if (product.getClass().equals(OfficeFurniture.class)) {
            OfficeFurniture officeFurniture = (OfficeFurniture) product;
            System.out.println(officeFurniture);
        } else if (product.getClass().equals(Electronic.class)) {
            Electronic electronic = (Electronic) product;
            System.out.println(electronic);
        } else {
            throw new IllegalStateException("Unexpected value: " + product.getClass());
        }


    }
}