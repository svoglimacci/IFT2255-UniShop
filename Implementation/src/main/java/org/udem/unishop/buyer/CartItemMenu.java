package org.udem.unishop.buyer;

import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Buyer;

/**
 * The CartItemMenu class represents a submenu specifically designed for managing a single cart item.
 * It allows users to perform actions related to a specific item in their shopping cart, such as removing it.
 */
public class CartItemMenu extends SubMenu {

  private final CartItem cartItem;
  private final ProductController productController;
  private final UserController userController;
  private final Buyer currentUser;

  /**
   * Constructs a CartItemMenu instance for a particular cart item.
   *
   * @param cartItem          The cart item being managed within the menu.
   * @param productController The controller handling product-related operations.
   * @param userController    The controller managing user-related actions.
   * @param currentUser       The current logged-in buyer user.
   */
  public CartItemMenu(CartItem cartItem, ProductController productController,
                        UserController userController, Buyer currentUser) {
    super(cartItem.getName());
    this.cartItem = cartItem;
    this.productController = productController;
    this.userController = userController;
    this.currentUser = currentUser;

    initializeMenu();
  }

  /**
   * Initializes the submenu by creating and adding menu components, such as commands or items.
   */
  private void initializeMenu() {
    Command removeCartItemCommand = createRemoveCartItemCommand();
    addMenuComponent(new MenuItem(removeCartItemCommand));
  }

  /**
   * Creates the command for removing the current cart item from the user's shopping cart.
   *
   * @return The command to remove the cart item.
   */
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