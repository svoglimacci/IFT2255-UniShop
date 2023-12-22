package org.udem.unishop.buyer;

import java.util.List;
import java.util.UUID;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.validation.StringValidator;

public class CheckoutMenu {

  private final SubMenu checkoutMenu;



  private final UserController userController;

  private final OrderController orderController;

  private final Buyer currentUser;

  private final boolean usePoints;

  public CheckoutMenu(Buyer currentUser, UserController userController, OrderController orderController) {
    this.currentUser = currentUser;
    this.userController = userController;
    this.orderController = orderController;
    this.usePoints = false;



    this.checkoutMenu = new SubMenu("Passer la commande");

    CheckoutPrompt checkoutPrompt = new CheckoutPrompt();

    checkoutMenu.addMenuComponent(new MenuItem(createCheckoutPrompt()));
    checkoutMenu.addMenuComponent(new MenuItem(createCheckoutCommand()));

  }

  public SubMenu getCheckoutMenu() {
    return checkoutMenu;
  }

  private Command createCheckoutPrompt() {
    CheckoutPrompt checkoutPrompt = new CheckoutPrompt();
    return new Command() {
      @Override
      public String getName() {
        return "Spécifier les informations de paiement";
      }

      @Override
      public void execute() {
        List<String> input = checkoutPrompt.createCheckoutPrompt().getValuesFromUser();
        List<UUID> orderId = orderController.createOrder(currentUser, usePoints);

          if (orderId != null) {
            System.out.println("La Commande a été passée avec succès");
            for (UUID id : orderId) {
              System.out.println("Numéro(s) de commande(s): " + id);
            }
          } else {
            System.out.println("Votre commande n'a pas pu être passée");

          }
      }
    };
  }

  private Command createCheckoutCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Utiliser les informations de paiement enregistrées";
      }

      @Override
      public void execute() {
            Prompt prompt = new Prompt();
    prompt.addPromptComponent(new PromptItem("Souhaitez-vous utiliser vos points de fidélité? (Y/N)", new StringValidator()));
    List<String> input = prompt.getValuesFromUser();
    boolean usePoints = (input.get(0).equals("Y") || input.get(0).equals("y"));

          List<UUID> orderId = orderController.createOrder(currentUser, usePoints);

          if (orderId != null) {
            System.out.println("La Commande a été passée avec succès");
            System.out.println("Numéro(s) de commande(s): ");
            for (UUID id : orderId) {
              System.out.println(id);
            }
          } else {
            System.out.println("Votre commande n'a pas pu être passée");

          }
        }

    };
  }

}