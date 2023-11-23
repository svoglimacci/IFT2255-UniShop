package org.udem.unishop.authentication;

import java.util.List;
import org.udem.unishop.buyer.BuyerMenu;
import org.udem.unishop.common.GuestMenu;
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

public class MainMenu {

  private final Menu mainMenu;
  private final UserController userController;
  private final ProductController productController;

  public MainMenu(UserController userController, ProductController productController) {
    this.userController = userController;
    this.productController = productController;
    this.mainMenu = new Menu();

    SubMenu registerMenu = createRegisterMenu();
    SubMenu loginMenu = createLoginMenu();

    this.mainMenu.addMenuComponent(registerMenu);
    this.mainMenu.addMenuComponent(loginMenu);
    this.mainMenu.addMenuComponent(new MenuItem(continueAsGuest()));
  }

  private SubMenu createRegisterMenu() {
    SubMenu registerMenu = new SubMenu("S'inscrire");
    registerMenu.addMenuComponent(new MenuItem(registerUser(AccountType.BUYER)));
    registerMenu.addMenuComponent(new MenuItem(registerUser(AccountType.SELLER)));
    return registerMenu;
  }

  private SubMenu createLoginMenu() {
    SubMenu loginMenu = new SubMenu("Se connecter");
    loginMenu.addMenuComponent(new MenuItem(loginUser(AccountType.BUYER)));
    loginMenu.addMenuComponent(new MenuItem(loginUser(AccountType.SELLER)));
    return loginMenu;
  }

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
            SellerMenu userMenu = new SellerMenu(userController, productController, (Seller) loggedInUser);
            userMenu.run();
          } else {
            BuyerMenu userMenu = new BuyerMenu(userController, productController, (Buyer) loggedInUser);
            userMenu.run();
          }

        } else {
          System.out.println("Erreur de connexion. Veuillez réessayer.");

        }

      }
    };
  }

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

  public void run() {
    this.mainMenu.execute();
  }
}