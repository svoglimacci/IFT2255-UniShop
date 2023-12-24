package org.udem.unishop.buyer;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Buyer;

/**
 * The CartItemPage class represents a page that displays details of a single item in the user's shopping cart.
 * It allows users to view information about the cart item and interact with its associated menu for actions.
 */
public class CartItemPage {

  private final CartItem cartItem;
  private final ProductController productController;
  private final UserController userController;
  private final Buyer currentUser;

  /**
   * Constructs a CartItemPage instance for displaying details and actions related to a cart item.
   *
   * @param cartItem          The cart item being displayed on the page.
   * @param productController The controller managing product-related operations.
   * @param userController    The controller handling user-related actions.
   * @param currentUser       The current logged-in buyer user.
   */
  public CartItemPage(CartItem cartItem, ProductController productController,
                        UserController userController, Buyer currentUser) {
        this.cartItem = cartItem;
        this.productController = productController;
        this.userController = userController;
        this.currentUser = currentUser;
    }

    /**
     * Runs the CartItemPage, displaying details of the cart item and its associated menu for actions.
     * Prints out information about the cart item and initiates the cart item menu.
     */
    public void run() {
    System.out.println(cartItem.getName());
    System.out.println(cartItem.getPrice());
    System.out.println(cartItem.getQuantity());
    CartItemMenu cartItemMenu = new CartItemMenu(cartItem, productController, userController, currentUser);
    cartItemMenu.execute();
    }
}