package org.udem.unishop.common;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.utilities.Menu;

public class GuestMenu {

    private final Menu guestMenu;
    private final UserController userController;
    private final ProductController productController;


    public GuestMenu(UserController userController, ProductController productController) {
        this.userController = userController;
        this.productController = productController;


        this.guestMenu = new Menu();
        SearchMenu searchMenu = new SearchMenu(userController, productController, null);
        this.guestMenu.addMenuComponent(searchMenu.getSearchMenu());
    }

    public void run() {
        guestMenu.execute();
    }
}