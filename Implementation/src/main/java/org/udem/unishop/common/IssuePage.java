package org.udem.unishop.common;

import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Issue;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.IssueState;

public class IssuePage {


  private final Issue issue;

  private final Order order;

  private final ProductController productController;

  private final UserController userController;

  private final OrderController orderController;

  private final User currentUser;

  public IssuePage(Order order, ProductController productController, UserController userController, OrderController orderController, User currentUser) {
    this.issue = order.getIssue();
    this.order = order;
    this.productController = productController;
    this.userController = userController;
    this.orderController = orderController;
    this.currentUser = currentUser;
  }

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