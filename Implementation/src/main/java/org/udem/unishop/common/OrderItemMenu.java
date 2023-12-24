package org.udem.unishop.common;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.udem.unishop.buyer.CheckoutMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.OrderState;
import org.udem.unishop.utilities.SubMenu;

/**
 * The OrderItemMenu class represents a menu for managing individual orders within the online shopping system.
 * It allows users to perform various actions based on the order status, such as confirming delivery, reporting issues,
 * initiating returns or exchanges, and more.
 */
public class OrderItemMenu extends SubMenu {

  private final Order order;

  private final ProductController productController;

  private final UserController userController;

  private final OrderController orderController;

  private final User currentUser;

  /**
   * Constructs an OrderItemMenu instance for managing actions related to a specific order.
   *
   * @param order             The order being managed.
   * @param productController The controller managing product-related functionalities.
   * @param userController    The controller handling user-related operations.
   * @param orderController   The controller managing order-related actions.
   * @param currentUser       The current user interacting with the order menu.
   */
  public OrderItemMenu(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
    super("Produits");
    this.order = order;
    this.productController = productController;
    this.userController = userController;
    this.orderController = orderController;
    this.currentUser = currentUser;

    initializeMenu();
  }

  /**
   * Initializes the menu components based on the order's status and the current user type (Buyer/Seller).
   */
  private void initializeMenu() {
    if(currentUser instanceof Buyer) {
      Command confirmDeliveryCommand = createConfirmDeliveryCommand();

      if(order.getStatus().equals(OrderState.DELIVERED) && order.getOrderDate() - System.currentTimeMillis() < 31536000000L) {

        if(order.getIssue() == null) {
          Command reportProblemCommand = createReportProblemCommand();
          addMenuComponent(new MenuItem(reportProblemCommand));
        }
        else {
          Command showIssueCommand = createShowIssueCommand();
          addMenuComponent(new MenuItem(showIssueCommand));
        }
      }



      Command returnOrderCommand = createReturnOrderCommand(false);
      Command exchangeOrderCommand = createReturnOrderCommand(true);

      if(order.getStatus().equals(OrderState.DELIVERING)) {
        addMenuComponent(new MenuItem(confirmDeliveryCommand));

      }

      if (order.getStatus().equals(OrderState.DELIVERED)) {
        long currentTime = System.currentTimeMillis();
        long orderDate = order.getOrderDate();
        long elapsedTime = currentTime - orderDate;
        long elapsedDays = TimeUnit.MILLISECONDS.toDays(elapsedTime);
        if(elapsedDays < 30) {
          addMenuComponent(new MenuItem(returnOrderCommand));
          addMenuComponent(new MenuItem(exchangeOrderCommand));
        }
      }
    }
    else {
      if (currentUser instanceof Seller) {

        if (order.getStatus().equals(OrderState.DELIVERED)) {
          Command showIssueCommand = createShowIssueCommand();
          if (order.getIssue() != null) {
            addMenuComponent(new MenuItem(showIssueCommand));
          }
        }

        if (order.getStatus() == OrderState.AWAITING_RETURN) {
          Command showConfirmReturnCommand = createConfirmDeliveryCommand();
          addMenuComponent(new MenuItem(showConfirmReturnCommand));
        }

        if(order.getStatus() == OrderState.AWAITING_EXCHANGE) {
          Command showConfirmExchangeCommand = createShowDeliveryCommand();
          addMenuComponent(new MenuItem(showConfirmExchangeCommand));
        }
      }

      if (order.getStatus().equals(OrderState.IN_PRODUCTION)) {
        Command modifyOrderCommand = createModifyOrderCommand();
        addMenuComponent(new MenuItem(modifyOrderCommand));
      }
    }

    }

  /**
   * Creates a command to show the delivery status for replacement products.
   *
   * @return The command for showing the delivery status of replacement products.
   */
  private Command createShowDeliveryCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Expédier le(s) produit(s) de remplacement";
      }

      @Override
      public void execute() {
        orderController.modifyOrderStatus(order, currentUser);
        System.out.println("Expédition de la commande #" + order.getId());
      }
    };
  }

  /**
   * Creates a command to display the issue associated with the order.
   *
   * @return The command for displaying the issue associated with the order.
   */
  private Command createShowIssueCommand() {

    IssuePage issuePage = new IssuePage(order, productController, userController,
        orderController, currentUser);

    return new Command() {
      @Override
      public String getName() {
        return "Voir le problème";
      }

      @Override
      public void execute() {
        issuePage.run();
      }
    };

  }

  /**
   * Creates a command to modify the order's status.
   *
   * @return The command for modifying the order's status.
   */
  private Command createModifyOrderCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Modifier l'état de la commande";
      }

      @Override
      public void execute() {
        orderController.modifyOrderStatus(order, currentUser);
        System.out.println("Commande #" + order.getId() + " modifiée");
      }
    };
  }

  /**
   * Creates a command to confirm the delivery of the order.
   *
   * @return The command for confirming the delivery of the order.
   */
  private Command createConfirmDeliveryCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Confirmer la livraison";
      }

      @Override
      public void execute() {
        orderController.modifyOrderStatus(order, currentUser);
        System.out.println("Commande #" + order.getId() + " confirmée");
      }
    };
  }

  /**
   * Creates a command to report an issue with the order.
   *
   * @return The command for reporting an issue with the order.
   */
  private Command createReportProblemCommand() {
    ReportPrompt reportPrompt = new ReportPrompt();
    return new Command() {
      @Override
      public String getName() {
        return "Signaler un problème";
      }

      @Override
      public void execute() {
        List<String> inputs = reportPrompt.createReportPrompt().getValuesFromUser();
        orderController.reportProblem(order, inputs);
        System.out.println("Problème signalé pour la commande #" + order.getId());
      }
    };
  }

  /**
   * Creates a command to handle returning or exchanging an order.
   *
   * @param isExchange Indicates whether the action is an exchange or return.
   * @return The command for returning or exchanging an order.
   */
  private Command createReturnOrderCommand(boolean isExchange) {

    return new Command() {
      @Override
      public String getName() {
        return "Effectuer un" + (isExchange ? " échange" : " retour");
      }

      @Override
      public void execute() {

        ReturnMenu returnMenu = new ReturnMenu((Buyer) currentUser, order, orderController, userController, productController, isExchange);
        returnMenu.run();


      }
    };
  }


}