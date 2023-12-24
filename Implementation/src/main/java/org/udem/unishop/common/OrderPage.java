package org.udem.unishop.common;
import java.util.UUID;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;

/**
 * The OrderPage class represents a page displaying details of a specific order.
 * It provides methods to display order information and initiate actions related to the order.
 */
public class OrderPage {

  private final Order order;
  private final ProductController productController;
  private final UserController userController;
  private final OrderController orderController;
  private final User currentUser;

  /**
   * Constructs an OrderPage object with the required controllers and current user.
   *
   * @param order            The order to be displayed on the page.
   * @param productController The controller for product-related functionalities.
   * @param userController    The controller for user-related functionalities.
   * @param orderController   The controller for order-related functionalities.
   * @param currentUser       The current user accessing the order page.
   */
  public OrderPage(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
    this.order = order;
    this.productController = productController;
    this.userController = userController;
    this.orderController = orderController;
    this.currentUser = currentUser;
  }

  /**
   * Displays the details of the order, including its ID, total price (for buyers),
   * status, and the list of products included in the order.
   * Initiates the execution of the OrderItemMenu for further actions on the order.
   */
  public void run() {
    System.out.println("Commande #" + order.getId());
    if (currentUser instanceof Buyer) {
      System.out.println("Total: " + order.getPrice() + "$");
    }
    System.out.println("Statut: " + order.getStatus());
    System.out.println("Produits: ");
    for (UUID id : order.getProducts()) {
      Product product = productController.getProductById(id);
      System.out.println(product.getName() + " - " + product.getPrice() + "$");
    }


    OrderItemMenu orderItemMenu = new OrderItemMenu(order, productController, userController, orderController, currentUser);
    orderItemMenu.execute();
  }

}