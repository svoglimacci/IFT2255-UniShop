package org.udem.unishop.buyer;

import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Cart;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

/**
 * The ShoppingCart class represents the shopping cart interface for a buyer in the online shopping system.
 * It displays the items in the cart, allows users to view individual cart items, and initiates the checkout process.
 */
public class ShoppingCart {

  private final SubMenu shoppingCart;

  private final Buyer currentUser;

  /**
   * Constructs a ShoppingCart instance for managing the buyer's shopping cart.
   *
   * @param currentUser       The current logged-in buyer user.
   * @param userController    The controller handling user-related operations.
   * @param productController The controller managing product-related functionalities.
   * @param orderController   The controller managing order-related actions.
   */
  public ShoppingCart(Buyer currentUser, UserController userController, ProductController productController, OrderController orderController) {
    this.currentUser = currentUser;
    Cart cart = currentUser.getCart();

    this.shoppingCart = new SubMenu("Panier d'achats");
    CheckoutMenu checkoutMenu = new CheckoutMenu(currentUser, userController, orderController);

    createCartItemList(productController, userController, cart);
    if (!cart.getItems().isEmpty()) {
          shoppingCart.addMenuComponent(checkoutMenu.getCheckoutMenu());
    }

  }

  /**
   * Runs the shopping cart interface, displaying cart items and initiating checkout if the cart is not empty.
   */
  public void run() {
    shoppingCart.execute();

  }

  /**
   * Creates the list of cart items and adds them to the shopping cart menu.
   *
   * @param productController The controller managing product-related functionalities.
   * @param userController    The controller handling user-related operations.
   * @param cart              The cart containing the items.
   */
  private void createCartItemList(ProductController productController, UserController userController, Cart cart) {

    if (cart.getItems().isEmpty()) {
      System.out.println("Votre panier est vide");
    } else {
      System.out.println("Total: " + cart.getTotalCost() + "$");
      System.out.println("Points de fidélité: " + cart.getTotalPoints() + " points");
      System.out.println("Articles dans le panier:");
    }

    for (CartItem object : cart.getItems()) {
      CartItemPage cartItemPage = new CartItemPage(object, productController, userController,
          currentUser);

      Command showCartItemCommand = new Command() {
        @Override
        public String getName() {
          return object.getName() + " - " + object.getQuantity() + " - " + object.getPrice() + "$";
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