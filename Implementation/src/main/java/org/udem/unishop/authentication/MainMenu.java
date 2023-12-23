package org.udem.unishop.authentication;

import java.util.List;
import org.udem.unishop.buyer.BuyerMenu;
import org.udem.unishop.common.GuestMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.seller.SellerMenu;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

/**
 * The MainMenu class represents the main menu of UniShop that includes options for registration, login and
 * guest access.
 * <p> The main menu allows users to navigate through different options and interact with the UniShop system. </p>
 */
public class MainMenu {

  private final Menu mainMenu;
  private final UserController userController;
  private final ProductController productController;

  private final OrderController orderController;

  /**
   * Constructs a MainMenu with the specified controllers.
   *
   * @param userController The controller for user-related operations.
   * @param productController The controller for product-related operations.
   * @param orderController The controller for order-related operations.
   */
  public MainMenu(UserController userController, ProductController productController, OrderController orderController) {
    this.userController = userController;
    this.productController = productController;
    this.orderController = orderController;

    this.mainMenu = new Menu();

    SubMenu registerMenu = createRegisterMenu();
    SubMenu loginMenu = createLoginMenu();

    this.mainMenu.addMenuComponent(registerMenu);
    this.mainMenu.addMenuComponent(loginMenu);
    this.mainMenu.addMenuComponent(new MenuItem(continueAsGuest()));
  }

  /**
   * Creates a registration menu with options for buyer and seller registration.
   *
   * @return The registration menu.
   */
  private SubMenu createRegisterMenu() {
    SubMenu registerMenu = new SubMenu("S'inscrire");
    registerMenu.addMenuComponent(new MenuItem(registerUser(AccountType.BUYER)));
    registerMenu.addMenuComponent(new MenuItem(registerUser(AccountType.SELLER)));
    return registerMenu;
  }

  /**
   * Creates a login menu with options for buyer and seller login.
   *
   * @return The login menu.
   */
  private SubMenu createLoginMenu() {
    SubMenu loginMenu = new SubMenu("Se connecter");
    loginMenu.addMenuComponent(new MenuItem(loginUser(AccountType.BUYER)));
    loginMenu.addMenuComponent(new MenuItem(loginUser(AccountType.SELLER)));
    return loginMenu;
  }

  /**
   * Creates a command for continuing as a guest.
   * @return The command for continuing as a guest.
   */
  private Command continueAsGuest() {
    return new Command() {

      /**
       * Gets the name of the command.
       *
       * @return The name of the command.
       */
      @Override
      public String getName() {
        return "Continuer en tant qu'invité";
      }

      /**
       * Executes the registration command based on the provided account type.
       */
      @Override
      public void execute() {
        GuestMenu guestMenu = new GuestMenu(userController, productController);
        guestMenu.run();
      }
    };
  }

  /**
   * Creates a command for user login based on the account type.
   *
   * @param accountType The type of account (buyer or seller).
   * @return The command for user login.
   */
  private Command loginUser(AccountType accountType) {
    LoginPrompt loginPrompt = new LoginPrompt();
    return new Command() {
      private final String statefulName = accountType == AccountType.BUYER ? "Acheteur" : "Vendeur";

      /**
       * Gets the name of the command.
       *
       * @return The name of the command.
       */
      @Override
      public String getName() {
        return statefulName;
      }

      /**
       * Executes the login command based on the account type and displays appropriate messages and navigates
       * to the user menu if login is successful.
       */
      @Override
      public void execute() {
        List<String> inputs = loginPrompt.createLoginPrompt().getValuesFromUser();

        User loggedInUser = userController.login(inputs, accountType);


        if(loggedInUser != null) {
          System.out.println("Vous êtes connecté en tant que " + loggedInUser.getUsername());
          if (accountType == AccountType.SELLER) {
            SellerMenu userMenu = new SellerMenu(userController, productController, orderController, (Seller) loggedInUser);
            userMenu.run();
          } else {
            BuyerMenu userMenu = new BuyerMenu(userController, productController, (Buyer) loggedInUser, orderController);
            userMenu.run();
          }

        } else {
          System.out.println("Erreur de connexion. Veuillez réessayer.");

        }

      }
    };
  }

  /**
   * Creates a command for user registration based on the account type.
   *
   * @param accountType The type of account (buyer or seller).
   * @return The command for user registration.
   */
  private Command registerUser(AccountType accountType) {
    RegisterPrompt registerPrompt = new RegisterPrompt();
    return new Command() {
      private String statefulName = accountType == AccountType.BUYER ? "Acheteur" : "Vendeur";

      @Override
      public String getName() {
        return statefulName;
      }

      @Override
      public void execute() {
        List<String> inputs = registerPrompt.createRegisterPrompt(accountType).getValuesFromUser();

        boolean isRegistered = userController.register(inputs, accountType);


        if(isRegistered) {
          System.out.println("Inscription réussie. Veuillez vous connecter dans les 24 heures afin de valider votre compte.");

        } else {
          System.out.println("Erreur lors de l'inscription. Veuillez réessayer.");

        }
      }
    };
  }

  /**
   * Runs the main menu, allowing users to navigate through different options.
   * <p>This method initiates the execution of the main menu, enabling users to interact with the UniShop program. </p>
   */
  public void run() {
    this.mainMenu.execute();
  }
}