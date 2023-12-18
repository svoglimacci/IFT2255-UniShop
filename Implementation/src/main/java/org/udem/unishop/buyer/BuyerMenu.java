package org.udem.unishop.buyer;

import org.udem.unishop.common.SearchMenu;
import org.udem.unishop.user.SettingsMenu;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;

public class BuyerMenu {

    private final Menu buyerMenu;
    private final UserController userController;
    private final ProductController productController;
    private final Buyer currentUser;



    public BuyerMenu(UserController userController, ProductController productController, Buyer currentUser) {
        this.userController = userController;
        this.productController = productController;
        this.currentUser = currentUser;


        buyerMenu = new Menu();
        SearchMenu searchMenu = new SearchMenu(userController, productController, currentUser);
        buyerMenu.addMenuComponent(searchMenu.getSearchMenu());
        buyerMenu.addMenuComponent(new MenuItem(createShoppingCart()));
        buyerMenu.addMenuComponent(new MenuItem(createSettingsMenu()));
        buyerMenu.addMenuComponent(new MenuItem(createFollowedMenu()));

    }

  private Command createFollowedMenu() {
        return new Command() {
            @Override
            public String getName() {
                return "Gérer les utilisateurs suivis";
            }

            @Override
            public void execute() {
              FollowedMenu followedMenu = new FollowedMenu(userController, productController, currentUser);
              followedMenu.run();

            }
        };
  }

  private Command createSettingsMenu() {
        return new Command() {
            @Override
            public String getName() {
                return "Modifier mon profil";
            }

            @Override
            public void execute() {
                SettingsMenu settingsMenu = new SettingsMenu(userController, currentUser);
                settingsMenu.run();
            }
        };
  }

  private Command createShoppingCart() {
        return new Command() {
            @Override
            public String getName() {
                return "Panier d'achat";
            }

            @Override
            public void execute() {
                ShoppingCart shoppingCart = new ShoppingCart(currentUser, userController,
                    productController);
                shoppingCart.run();
            }
        };
    }


    public void run() {
        buyerMenu.execute();
    }
}