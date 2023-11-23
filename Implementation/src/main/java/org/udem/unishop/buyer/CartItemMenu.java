package org.udem.unishop.buyer;

import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Buyer;

public class CartItemMenu extends SubMenu {

  private final CartItem cartItem;
  private final ProductController productController;
  private final UserController userController;
  private final Buyer currentUser;

  public CartItemMenu(CartItem cartItem, ProductController productController,
                        UserController userController, Buyer currentUser) {
    super(cartItem.getName());
    this.cartItem = cartItem;
    this.productController = productController;
    this.userController = userController;
    this.currentUser = currentUser;

    initializeMenu();
  }

  private void initializeMenu() {
    Command removeCartItemCommand = createRemoveCartItemCommand();
    addMenuComponent(new MenuItem(removeCartItemCommand));
  }

  private Command createRemoveCartItemCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Retirer du panier";
      }

      @Override
      public void execute() {
        userController.removeItemFromCart(currentUser, cartItem);
      }
    };
  }

}