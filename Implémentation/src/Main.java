import controllers.ProductController;
import controllers.UserController;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import models.Book;
import models.Buyer;
import models.Order;
import models.Order.orderState;
import models.Seller;
import views.AuthView;

import java.io.IOException;


/**
 * Plateforme UniShop : plateforme de commerce électronique crée dans le cadre du cours de Génie Logiciel
 * IFT2255 à l'Université de Montréal.
 *
 * @author : Simon Voglimacci   20002825
 * @author : Victor Lebblond    20244841
 * @author : Julie Yang         20239909
 * @author : Celina Zhang       20207461
 * @version : 1.0
 */


public class Main {

    public static void main(String[] args) throws IOException {
        UserController userController = new UserController();
        ProductController productController = new ProductController();

        initializeData(userController, productController);

        AuthView authView = new AuthView(userController, productController);
        authView.start();

    }

    private static void initializeData(UserController userController, ProductController productController) {


        // Add buyers with followers
        userController.register("John", "Doe", "john@example.com", "john", "password", "123 Main St", "1234567890");
        userController.register("Alice", "Smith", "alice@example.com", "alice", "password",
            "456 Oak St", "9876543210");
        userController.register("Bob", "Johnson", "bob@example.com", "bob", "password",
            "789 Maple St", "5551112233");
        userController.register("Eva", "Williams", "eva@example.com", "eva", "password",
            "101 Pine St", "5554445566");
        userController.register("Chris", "Miller", "chris@example.com", "chris", "password",
            "202 Cedar St", "5557778899");
        // Add sellers
        userController.register("BestElectronics", "best@example.com", "bestseller", "password",
            "789 Elm St", "5551234567");
        userController.register("TechGadgets", "tech@example.com", "techseller", "password",
            "321 Cedar St", "5555678901");
        userController.register("OfficeEssentials", "office@example.com", "officeseller", "password", "654 Fir St", "5552345678");
        userController.register("BookHaven", "books@example.com", "bookstore", "password",
            "456 Birch St", "5557890123");
        userController.register("FashionTrends", "fashion@example.com", "fashionseller", "password",
            "987 Pine St", "5553456789");

        productController.addBook("The Great Gatsby", "Classic novel", 19.99, 20,
            "978-0-7432-7356-5", "F. Scott Fitzgerald", "Scribner", "1925", "1st", "Vol1", "Fiction");
        productController.addElectronic("Smartwatch", "Fitness tracker", 79.99, 8, "FitTech", "ModelFit", "2022-02-15", "Wearables");
        productController.addOfficeSupply("Desk Organizer", "Keep your desk tidy", 29.99, 12, "OfficeMaster", "OrganizeX", "Desk Accessories");
        productController.addOfficeFurniture("Office Chair", "Ergonomic chair", 149.99, 5, "ComfortSeating", "ErgoComfort", "Task Chairs");
        productController.addLearningMaterial("Java Programming", "Programming guide", 49.99, 15, "BestBooks", "Java101", "Programming", "123-456-789", "John Doe", "Tech Publications", "2022-01-01", "1st Edition");


        userController.addProductsToSeller("bestseller", productController.getProducts().getBooks().get(0).getInstances());
        userController.addProductsToSeller("techseller", productController.getProducts().getElectronics().get(0).getInstances());
        userController.addProductsToSeller("officeseller", productController.getProducts().getOfficeSupplies().get(0).getInstances());
        userController.addProductsToSeller("officeseller", productController.getProducts().getOfficeFurnitures().get(0).getInstances());
        userController.addProductsToSeller("bookstore", productController.getProducts().getLearningMaterials().get(0).getInstances());

        Buyer johnBuyer = (Buyer) userController.getUsers(false).get(0);
        Seller bestSeller = (Seller) userController.getUsers(true).get(0);
        Book book = productController.getProducts().getBooks().get(0);
        Set<UUID> productsIds = new HashSet<>();
        productsIds.add(book.getId());

        userController.addReview(johnBuyer, productController.getProducts().getBooks().get(0), "Great book!", "5");

        Order order1 = new Order(UUID.randomUUID(), johnBuyer.getId(), bestSeller.getId(), productsIds, book.getPrice());

        Order order2 = new Order(UUID.randomUUID(), johnBuyer.getId(), bestSeller.getId(), productsIds, book.getPrice());
        order2.changeOrderStatus(Order.orderState.delivered);

        Order order3 = new Order(UUID.randomUUID(), johnBuyer.getId(), bestSeller.getId(), productsIds, book.getPrice());
        order3.changeOrderStatus(orderState.delivering);

        johnBuyer.addPurchase(book.getId());
        johnBuyer.addOrder(order1);
        bestSeller.addOrder(order1);
        johnBuyer.addOrder(order2);
        bestSeller.addOrder(order2);
        johnBuyer.addOrder(order3);
        bestSeller.addOrder(order3);



    }
}