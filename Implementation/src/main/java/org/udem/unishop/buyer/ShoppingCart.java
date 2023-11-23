package org.udem.unishop.buyer;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Cart;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

public class ShoppingCart {

  private final SubMenu shoppingCart;

  private final Buyer currentUser;

  public ShoppingCart(Buyer currentUser, UserController userController, ProductController productController) {
    this.currentUser = currentUser;
    this.shoppingCart = new SubMenu("Panier d'achats");

    createCartItemList(productController, userController);
  }

  public void run() {
    shoppingCart.execute();
  }

  private void createCartItemList(ProductController productController, UserController userController) {
    Cart cart = currentUser.getCart();

    if (cart.getItems().isEmpty()) {
      System.out.println("Votre panier est vide");
    }

    for (CartItem object : cart.getItems()) {
      CartItemPage cartItemPage = new CartItemPage(object, productController, userController,
          currentUser);

      Command showCartItemCommand = new Command() {
        @Override
        public String getName() {
          return object.getName();
        }

        @Override
        public void execute() {
          cartItemPage.run();

        }
      };

      shoppingCart.addMenuComponent(new MenuItem(showCartItemCommand));
    }
  }

}