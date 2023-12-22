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


public class IssueMenu extends SubMenu {

  private final Order order;
    private final Issue issue;

    private final ProductController productController;

    private final UserController userController;

    private final OrderController orderController;

    private final User currentUser;

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

  private Command createConfirmIssueDeliveryCommand() {
      return new Command() {
        @Override
        public String getName() {
          return "Confirmer la livraison";
        }

        @Override
        public void execute() {
          orderController.confirmDelivery(order);        }
      };
  }

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