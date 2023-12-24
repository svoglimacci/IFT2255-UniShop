package org.udem.unishop.common;

import java.util.List;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.Seller;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.IssueState;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.models.Issue;
import org.udem.unishop.models.User;

/**
 * The IssueMenu class represents a menu for managing issues related to orders.
 * It extends the SubMenu class and provides functionalities for issue handling
 * based on the status of the issue and the user role (Seller or other users).
 */
public class IssueMenu extends SubMenu {

  private final Order order;
    private final Issue issue;

    private final ProductController productController;

    private final UserController userController;

    private final OrderController orderController;

    private final User currentUser;

  /**
   * Constructs an IssueMenu object with provided parameters.
   *
   * @param order            The order associated with the issue menu.
   * @param productController The controller handling product-related functionalities.
   * @param userController   The controller managing user-related operations.
   * @param orderController  The controller responsible for order-specific tasks.
   * @param currentUser      The current user interacting with the issue menu.
   */
    public IssueMenu(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
      super(order.getIssue().getDescription());
      this.order = order;
      this.issue = order.getIssue();
      this.productController = productController;
      this.userController = userController;
      this.orderController = orderController;
      this.currentUser = currentUser;

      initializeMenu();
    }

  /**
   * Initializes the menu based on the status of the issue and the current user type.
   * Adds specific commands to the menu based on the issue's status and user type (Seller or Buyer).
   * For Sellers, includes options related to issue handling such as confirming returns and offering solutions.
   * For Buyers, includes options like accepting solutions and confirming issue deliveries.
   */
    private void initializeMenu() {


      if(currentUser instanceof Seller) {
        if(issue.getStatus() == IssueState.AWAITING_RETURN) {
          Command confirmIssueReturnCommand = createConfirmIssueReturnCommand();
          addMenuComponent(new MenuItem(confirmIssueReturnCommand));
        }
        if(issue.getStatus() == IssueState.OPEN) {
          Command offerSolutionCommand = createOfferSolutionCommand();
          addMenuComponent(new MenuItem(offerSolutionCommand));
        }

      } else {
        if(issue.getStatus() == IssueState.AWAITING) {
          Command acceptSolutionCommand = createAcceptSolutionCommand();
          addMenuComponent(new MenuItem(acceptSolutionCommand));
        }
        if(issue.getStatus() == IssueState.DELIVERING) {
          Command confirmIssueDeliveryCommand = createConfirmIssueDeliveryCommand();
          addMenuComponent(new MenuItem(confirmIssueDeliveryCommand));
        }
      }
    }

  /**
   * Creates a command to confirm the return of the order related to the issue.
   *
   * @return A command to execute the confirmation of the order return.
   */
  private Command createConfirmIssueReturnCommand() {
      return new Command() {
        @Override
        public String getName() {
          return "Confirmer le retour";
        }

        @Override
        public void execute() {
          orderController.confirmIssueReturn(order);
        }
      };
  }

  /**
   * Creates a command to confirm the delivery of the order related to the issue.
   *
   * @return A command to execute the confirmation of the order delivery.
   */
  private Command createConfirmIssueDeliveryCommand() {
      return new Command() {
        @Override
        public String getName() {
          return "Confirmer la livraison";
        }

        @Override
        public void execute() {
          orderController.confirmIssueDelivery(order);
          orderController.confirmDelivery(order);        }
      };
  }

  /**
   * Creates a command to accept the solution proposed for the issue.
   *
   * @return A command to execute the acceptance of the proposed solution for the issue.
   */
  private Command createAcceptSolutionCommand() {
      return new Command() {
        @Override
        public String getName() {
          return "Accepter la solution";
        }

        @Override
        public void execute() {
          orderController.acceptSolution(order);
        }
      };
    }

  /**
   * Creates a command to offer a solution for the issue.
   *
   * @return A command to execute the offering of a solution for the issue.
   */
    private Command createOfferSolutionCommand() {
      SolutionPrompt solutionPrompt = new SolutionPrompt();
      return new Command() {
        @Override
        public String getName() {
          return "Offrir une solution";
        }

        @Override
        public void execute() {
          List<String> input = solutionPrompt.createSolutionPrompt().getValuesFromUser();
          orderController.offerSolution(order, input.get(0));
          System.out.println("Solution offerte");
        }
      };
    }


}