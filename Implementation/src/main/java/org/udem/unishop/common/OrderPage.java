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

public class OrderPage {

  private final Order order;
  private final ProductController productController;
  private final UserController userController;
  private final OrderController orderController;
  private final User currentUser;

  public OrderPage(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
    this.order = order;
    this.productController = productController;
    this.userController = userController;
    this.orderController = orderController;
    this.currentUser = currentUser;
  }

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