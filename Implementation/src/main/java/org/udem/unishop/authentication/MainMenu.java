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
 * The MainMenu class represents the main menu of the application for user authentication and navigation.
 * It provides options for user registration, login, and guest access, directing users to their respective menus based on their roles.
 */
public class MainMenu {

  private final Menu mainMenu;
  private final UserController userController;
  private final ProductController productController;

  private final OrderController orderController;

  /**
   * Constructs a MainMenu instance for handling user authentication and navigation.
   *
   * @param userController    The controller handling user-related operations.
   * @param productController The controller managing product-related functionalities.
   * @param orderController   The controller managing order-related actions.
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
   * Creates the submenu for user registration options.
   *
   * @return The submenu for user registration.
   */
  private SubMenu createRegisterMenu() {
    SubMenu registerMenu = new SubMenu("S'inscrire");
    registerMenu.addMenuComponent(new MenuItem(registerUser(AccountType.BUYER)));
    registerMenu.addMenuComponent(new MenuItem(registerUser(AccountType.SELLER)));
    return registerMenu;
  }

  /**
   * Creates the submenu for user login options.
   *
   * @return The submenu for user login.
   */
  private SubMenu createLoginMenu() {
    SubMenu loginMenu = new SubMenu("Se connecter");
    loginMenu.addMenuComponent(new MenuItem(loginUser(AccountType.BUYER)));
    loginMenu.addMenuComponent(new MenuItem(loginUser(AccountType.SELLER)));
    return loginMenu;
  }

  /**
   * Initiates the application in guest mode.
   *
   * @return The command to continue as a guest user.
   */
  private Command continueAsGuest() {
    return new Command() {
      @Override
      public String getName() {
        return "Continuer en tant qu'invité";
      }

      @Override
      public void execute() {
        GuestMenu guestMenu = new GuestMenu(userController, productController);
        guestMenu.run();
      }
    };
  }


  /**
   * Handles user login functionality based on the specified account type.
   *
   * @param accountType The type of account (Buyer/Seller).
   * @return The command for user login based on the account type.
   */
  private Command loginUser(AccountType accountType) {
    LoginPrompt loginPrompt = new LoginPrompt();
    return new Command() {
      private final String statefulName = accountType == AccountType.BUYER ? "Acheteur" : "Vendeur";

      @Override
      public String getName() {
        return statefulName;
      }

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
   * Handles user registration based on the specified account type.
   *
   * @param accountType The type of account (Buyer/Seller).
   * @return The command for user registration based on the account type.
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
   * Executes the main menu to handle user actions.
   */
  public void run() {
    this.mainMenu.execute();
  }
}