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
public class OrderItemMenu extends SubMenu {

  private final Order order;

  private final ProductController productController;

  private final UserController userController;

  private final OrderController orderController;

  private final User currentUser;

  public OrderItemMenu(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
    super("Produits");
    this.order = order;
    this.productController = productController;
    this.userController = userController;
    this.orderController = orderController;
    this.currentUser = currentUser;

    initializeMenu();
  }

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
      Command modifyOrderCommand = createModifyOrderCommand();

      if(currentUser instanceof Seller && order.getStatus().equals(OrderState.DELIVERED)) {
        Command showIssueCommand = createShowIssueCommand();
        if (order.getIssue() != null) {
          addMenuComponent(new MenuItem(showIssueCommand));
        }
      }

        if(order.getStatus() == OrderState.AWAITING_RETURN) {
          Command showConfirmReturnCommand = createConfirmDeliveryCommand();
          addMenuComponent(new MenuItem(showConfirmReturnCommand));
        }
      }

      if(order.getStatus().equals(OrderState.IN_PRODUCTION)) {
        Command modifyOrderCommand = createModifyOrderCommand();
        addMenuComponent(new MenuItem(modifyOrderCommand));
      }

    }

  private Command createShowDeliveryCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Expédier le(s) produit(s) de remplacement";
      }

      @Override
      public void execute() {
        //orderController.showDelivery(order);
        System.out.println("Expédition de la commande #" + order.getId());
      }
    };
  }

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