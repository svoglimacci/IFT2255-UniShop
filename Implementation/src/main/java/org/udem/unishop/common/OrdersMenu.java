package org.udem.unishop.common;

import java.util.List;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

public class OrdersMenu {

  private final SubMenu ordersMenu;

  private final UserController userController;
  private final ProductController productController;

  private final OrderController orderController;
  private final User currentUser;

  public OrdersMenu(UserController userController, ProductController productController, OrderController orderController, User currentUser) {
    this.userController = userController;
    this.productController = productController;
    this.orderController = orderController;
    this.currentUser = currentUser;
    this.ordersMenu = new SubMenu("Mes commandes");
    createOrdersList(productController, userController, orderController);

  }

  public void run() {
    ordersMenu.execute();
  }

  private void createOrdersList(ProductController productController, UserController userController, OrderController orderController) {
    List<Order> orders = currentUser.getOrderList();

    if (orders.isEmpty()) {
      System.out.println("Vous n'avez pas de commandes");
    }

    for (Order order : orders) {
      OrderPage orderPage = new OrderPage(order, productController, userController, orderController, currentUser);

      Command showOrderCommand = new Command() {
        @Override
        public String getName() {
          return order.getId() + " - " + order.getPrice() + "$" + " - " + order.getStatus().name();
        }

        @Override
        public void execute() {
          orderPage.run();

        }
      };

      ordersMenu.addMenuComponent(new MenuItem(showOrderCommand));
    }

  }




}