package org.udem.unishop.buyer;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Buyer;

public class CartItemPage {

  private final CartItem cartItem;
  private final ProductController productController;
  private final UserController userController;
  private final Buyer currentUser;

  public CartItemPage(CartItem cartItem, ProductController productController,
                        UserController userController, Buyer currentUser) {
        this.cartItem = cartItem;
        this.productController = productController;
        this.userController = userController;
        this.currentUser = currentUser;
    }

    public void run() {
    System.out.println(cartItem.getName());
    System.out.println(cartItem.getPrice());
    System.out.println(cartItem.getQuantity());

    CartItemMenu cartItemMenu = new CartItemMenu(cartItem, productController, userController, currentUser);
    cartItemMenu.execute();
    }



}