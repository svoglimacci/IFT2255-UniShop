package org.udem.unishop.common;

import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Issue;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.IssueState;

/**
 * The IssuePage class represents the page displaying information about an issue related to an order.
 * It provides details about the issue, such as description, status, and solution, and handles issue-related functionalities.
 */
public class IssuePage {


  private final Issue issue;

  private final Order order;

  private final ProductController productController;

  private final UserController userController;

  private final OrderController orderController;

  private final User currentUser;

  /**
   * Constructs an IssuePage object for the provided order and related controllers.
   *
   * @param order            The order associated with the issue.
   * @param productController The controller for product-related functionalities.
   * @param userController   The controller for user-related functionalities.
   * @param orderController  The controller for order-related functionalities.
   * @param currentUser      The current user accessing the issue page.
   */
  public IssuePage(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
    this.issue = order.getIssue();
    this.order = order;
    this.productController = productController;
    this.userController = userController;
    this.orderController = orderController;
    this.currentUser = currentUser;
  }

  /**
   * Displays information about the issue, its status, and solution.
   * Executes functionalities based on the issue status, such as cancellation or providing tracking information.
   * Also executes the IssueMenu for further actions related to the issue.
   */
  public void run() {
    System.out.println("Description: " + issue.getDescription());
    System.out.println("Statut: " + issue.getStatus().name());
    System.out.println("Solution: " + issue.getSolution());

    if(issue.getTrackingNumberDate() != 0) {
      if(issue.getTrackingNumberDate() - System.currentTimeMillis() < 259200000L) {

        System.out.println("Numéro de suivi: " + issue.getTrackingNumber());
      } else {
        orderController.cancelIssueReturn(order);
      }
    } else {

      if (issue.getStatus() == IssueState.AWAITING_RETURN) {
        System.out.println("Veuillez réexpédier avec le numéro de suivi suivant:");
        System.out.println("Numéro de suivi: " + issue.getTrackingNumber());
      }
    }




    IssueMenu issueMenu = new IssueMenu(order, productController, userController, orderController, currentUser);
    issueMenu.execute();

  }

}