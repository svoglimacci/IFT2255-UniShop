package org.udem.unishop.user;

import java.util.UUID;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;

public class UserPage {

    private final User user;
    private final ProductController productController;
    private final UserController userController;
    private final User currentUser;

    public UserPage(User user, ProductController productController,
                       UserController userController, User currentUser) {
        this.user = user;
        this.productController = productController;
        this.userController = userController;
        this.currentUser = currentUser;
    }

    public void run() {
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getAccountType().displayName());

        if(user instanceof Buyer buyer) {
            System.out.println(buyer.getFirstName());
            System.out.println(buyer.getLastName());

        }

        if(user instanceof Seller seller) {
            System.out.println(seller.getCompanyName());
            System.out.println(seller.getAddress());

            for (UUID productId : seller.getProductsIds()) {
                Product product = productController.getProductById(productId);
                if (product != null ) {System.out.println(product.getName()); }
            }
        }


        UserMenu userMenu = new UserMenu(user, userController, productController, currentUser);
        userMenu.execute();
    }


}