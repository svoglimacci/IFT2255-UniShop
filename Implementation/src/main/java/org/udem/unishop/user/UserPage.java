package org.udem.unishop.user;

import java.util.UUID;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;

/**
 * The UserPage class represents a page that displays information about a user, including details such as username,
 * followers, liked products (for buyers), and listed products (for sellers).
 */
public class UserPage {

    private final User user;
    private final ProductController productController;
    private final UserController userController;
    private final User currentUser;

    /**
     * Constructor for UserPage.
     *
     * @param user The user for whom the page is created.
     * @param productController The ProductController instance for product-related operations.
     * @param userController The UserController instance for user-related operations.
     * @param currentUser The current user viewing the page.
     */
    public UserPage(User user, ProductController productController,
                       UserController userController, User currentUser) {
        this.user = user;
        this.productController = productController;
        this.userController = userController;
        this.currentUser = currentUser;
    }

    /**
     * Displays information about the user, including their username, followers, liked products (for buyers),
     * and listed products (for sellers), and creates and executes a UserMenu to provide interaction options.
     */
    public void run() {

        if(user instanceof Buyer buyer) {
          System.out.println("Pseudonyme: " + buyer.getUsername());
          System.out.println("Followers: " + buyer.getFollowers().size());
            System.out.println("Mentions j'aime: ");
            for (UUID productId : buyer.getLikedProducts()) {
                Product product = productController.getProductById(productId);
                if (product != null ) {
                    System.out.println(" - " + product.getName());
                }
            }
        }

        if(user instanceof Seller seller) {
            System.out.println("Nom du revendeur: " + seller.getCompanyName());
            System.out.println("Addresse: " + seller.getAddress());

            System.out.println("Produits:");
            for (UUID productId : seller.getProductsIds()) {
                Product product = productController.getProductById(productId);
                if (product != null ) {
                    System.out.println(" - " + product.getName());
                }
            }
        }


        UserMenu userMenu = new UserMenu(user, userController, productController, currentUser);
        userMenu.execute();
    }


}