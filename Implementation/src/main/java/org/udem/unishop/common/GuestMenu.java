package org.udem.unishop.common;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.utilities.Menu;

/**
 * The GuestMenu class represents the menu interface for guest users in the online shopping system.
 * It provides limited functionalities available to guests, such as searching for products.
 */
public class GuestMenu {

    private final Menu guestMenu;
    private final UserController userController;
    private final ProductController productController;

    /**
     * Constructs a GuestMenu instance for guest users.
     *
     * @param userController    The controller handling user-related operations.
     * @param productController The controller managing product-related functionalities.
     */
    public GuestMenu(UserController userController, ProductController productController) {
        this.userController = userController;
        this.productController = productController;


        this.guestMenu = new Menu();
        SearchMenu searchMenu = new SearchMenu(userController, productController, null);
        this.guestMenu.addMenuComponent(searchMenu.getSearchMenu());
    }

    /**
     * Runs the guest menu, allowing access to limited functionalities available to guests.
     */
    public void run() {
        guestMenu.execute();
    }
}