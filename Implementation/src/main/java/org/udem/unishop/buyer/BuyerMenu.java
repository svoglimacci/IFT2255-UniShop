package org.udem.unishop.buyer;

import org.udem.unishop.common.MetricsPage;
import org.udem.unishop.common.NotificationsMenu;
import org.udem.unishop.common.OrdersMenu;
import org.udem.unishop.common.SearchMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.user.SettingsMenu;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuComponent;
import org.udem.unishop.utilities.MenuItem;

/**
 * The BuyerMenu class represents the menu interface for a buyer in the online shopping system.
 * It provides access to various functionalities such as managing notifications, viewing metrics,
 * handling orders, managing followed users, modifying profile settings, and accessing the shopping cart.
 * This menu integrates with controllers and user-related functionalities for seamless interaction.
 */
public class BuyerMenu {

    private final Menu buyerMenu;
    private final UserController userController;
    private final ProductController productController;

    private final OrderController orderController;
    private final Buyer currentUser;


    /**
     * Constructs a BuyerMenu instance.
     *
     * @param userController   The controller handling user-related operations.
     * @param productController The controller managing product-related functionalities.
     * @param currentUser      The current logged-in buyer user.
     * @param orderController  The controller managing order-related actions.
     */
    public BuyerMenu(UserController userController, ProductController productController, Buyer currentUser, OrderController orderController) {
        this.userController = userController;
        this.productController = productController;
        this.orderController = orderController;
        this.currentUser = currentUser;


        buyerMenu = new Menu();
        SearchMenu searchMenu = new SearchMenu(userController, productController, currentUser);
        buyerMenu.addMenuComponent(searchMenu.getSearchMenu());
        buyerMenu.addMenuComponent(new MenuItem(createShoppingCart()));
        buyerMenu.addMenuComponent(new MenuItem(createSettingsMenu()));
        buyerMenu.addMenuComponent(new MenuItem(createFollowedMenu()));
        buyerMenu.addMenuComponent(createMetricsMenu());
        buyerMenu.addMenuComponent(createNotificationsMenu());
        buyerMenu.addMenuComponent(createOrdersMenu());

    }
  /**
   * Creates the notifications menu component.
   *
   * @return The menu component for managing notifications.
   */
  private MenuComponent createNotificationsMenu() {
      return new MenuItem(new Command() {
  @Override
          public String getName() {
              return "Mes notifications" + (!currentUser.getNotifications().isEmpty() ? " (" + currentUser.getNotifications().size() + ")" : "");
          }

          @Override
          public void execute() {
              NotificationsMenu notificationsMenu = new NotificationsMenu(userController, productController, currentUser);
              notificationsMenu.run();
          }
      });
  }

  /**
   * Creates the metrics menu component.
   *
   * @return The menu component for viewing metrics.
   */
  private MenuComponent createMetricsMenu() {
      return new MenuItem(new Command() {
  @Override
          public String getName() {
              return "Mes métriques";
          }

          @Override
          public void execute() {
            MetricsPage metricsPage = new MetricsPage(currentUser, userController, productController, orderController);
            metricsPage.run();
          }
      });
    }

  /**
   * Creates the orders menu component.
   *
   * @return The menu component for handling orders.
   */
  private MenuComponent createOrdersMenu() {
    return new MenuItem(new Command() {
      @Override
      public String getName() {
        return "Mes commandes";
      }

      @Override
      public void execute() {
        OrdersMenu ordersMenu = new OrdersMenu(userController, productController, orderController,
            currentUser);
        ordersMenu.run();
      }
    });
  }

  /**
   * Creates the command for managing followed users.
   *
   * @return The command for managing followed users.
   */
  private Command createFollowedMenu() {
        return new Command() {
            @Override
            public String getName() {
                return "Gérer les utilisateurs suivis";
            }

            @Override
            public void execute() {
              FollowedMenu followedMenu = new FollowedMenu(userController, productController, currentUser);
              followedMenu.run();

            }
        };
  }

  /**
   * Creates the command for modifying profile settings.
   *
   * @return The command for modifying profile settings.
   */
  private Command createSettingsMenu() {
        return new Command() {
            @Override
            public String getName() {
                return "Modifier mon profil";
            }

            @Override
            public void execute() {
                SettingsMenu settingsMenu = new SettingsMenu(userController, currentUser);
                settingsMenu.run();
            }
        };
  }

  /**
   * Creates the command for accessing the shopping cart.
   *
   * @return The command for accessing the shopping cart.
   */
  private Command createShoppingCart() {
        return new Command() {
            @Override
            public String getName() {
                return "Panier d'achat";
            }

            @Override
            public void execute() {
                ShoppingCart shoppingCart = new ShoppingCart(currentUser, userController,
                    productController, orderController);
                shoppingCart.run();
            }
        };
    }


    /**
     * Executes the buyer menu, initiating the interaction with the menu components.
     */
    public void run() {
        buyerMenu.execute();
    }
}