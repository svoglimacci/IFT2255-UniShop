package org.udem.unishop.seller;

import java.util.List;
import org.udem.unishop.common.MetricsPage;
import org.udem.unishop.common.NotificationsMenu;
import org.udem.unishop.common.OrdersMenu;
import org.udem.unishop.common.SearchMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Seller;
import org.udem.unishop.product.ProductPrompt;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuComponent;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.ProductType;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.utilities.Command;

/**
 * Reperesents the menu for sellers with various features
 */
public class SellerMenu {

    private final Menu sellerMenu;

    private final Menu ordersMenu;
    private final UserController userController;
    private final ProductController productController;

    private final OrderController orderController;

    private final Seller currentUser;


    /**
     * Constructs a SellerMenu for the specified sller.
     * @param userController The user controller.
     * @param productController The product controller.
     * @param orderController The order controller.
     * @param currentUser The currect seller user.
     */
    public SellerMenu(UserController userController, ProductController productController, OrderController orderController, Seller currentUser) {
        this.userController = userController;
        this.productController = productController;
        this.orderController = orderController;
        this.currentUser = currentUser;

        SearchMenu searchMenu = new SearchMenu(userController, productController, currentUser);
        this.sellerMenu = new Menu();
        this.ordersMenu = new Menu();
        this.sellerMenu.addMenuComponent(searchMenu.getSearchMenu());
         this.sellerMenu.addMenuComponent(createAddProductMenu());
         this.sellerMenu.addMenuComponent(createOrdersMenu());
         sellerMenu.addMenuComponent(createMetricsMenu());
        sellerMenu.addMenuComponent(createNotificationsMenu());
    }

    /**
     * Creates a menu component for handling seller orders.
     *
     * @return The created menu component.
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
     * Runs the seller menu.
     */
    public void run() {
        sellerMenu.execute();
    }

    /**
     * Creates a menu component for handling seller notifications.
     *
     * @return The created menu component.
     */
    private MenuComponent createNotificationsMenu() {
      return new MenuItem(new Command() {
          /**
           * Gets the command name for user notifications.
           *
           * @return The formatted command name.
           */
          @Override
          public String getName() {
              return "Mes notifications" + (!currentUser.getNotifications().isEmpty() ? " (" + currentUser.getNotifications().size() + ")" : "");
          }

          /**
           * Executes the command related to user notifications.
           * It initiates the display and interaction with the notifications menu for the current user.
           */
          @Override
          public void execute() {
              NotificationsMenu notificationsMenu = new NotificationsMenu(userController, productController, currentUser);
              notificationsMenu.run();
          }
      });
    }

    /**
     * Creates a menu component for handling seller metrics.
     *
     * @return The created menu component.
     */
    private MenuComponent createMetricsMenu() {
        return new MenuItem(new Command() {
            /**
             * Gets the command name for displaying user metrics.
             *
             * @return The string "Mes métriques" representing the command name.
             */
            @Override
            public String getName() {
              return "Mes métriques";
          }

            /**
             * Executes the command to display user metrics.
             * It creates an instance of MetricsPage and runs it to show the metrics for the current user.
             */
              @Override
              public void execute() {
                MetricsPage metricsPage = new MetricsPage(currentUser, userController, productController, orderController);
                metricsPage.run();
              }
        });
    }

    /**
     * Creates a submenu for adding a product with different types.
     *
     * @return The created submenu.
     */
    private SubMenu createAddProductMenu() {
        SubMenu addProductMenu = new SubMenu("Ajouter un produit");

        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.BOOK)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.COMPUTER_HARDWARE)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.LEARNING_RESOURCE)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.OFFICE_EQUIPMENT)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.STATIONERY)));

        return addProductMenu;
    }

    /**
     * Creates a command for adding a product of the specified type.
     *
     * @param productType The type of product to add.
     * @return The created command.
     */
    private Command createAddProductCommand(ProductType productType) {
        ProductPrompt productPrompt = new ProductPrompt();
        return new Command() {
            /**
             * Gets the display name for a specific product type.
             *
             * @return The display name of the associated product type.
             */
            @Override
            public String getName() {
                return productType.displayName();
            }

            /**
             * Executes the command to add a product of a specific type.
             */
            @Override
            public void execute() {
                List<String> inputs = productPrompt.createProductPrompt(productType).getValuesFromUser();
                boolean productCreated = switch (productType) {
                  case BOOK -> productController.addBook(inputs, currentUser.getId());
                  case LEARNING_RESOURCE ->
                      productController.addLearningResource(inputs, currentUser.getId());
                  case STATIONERY -> productController.addStationery(inputs, currentUser.getId());
                  case OFFICE_EQUIPMENT ->
                      productController.addOfficeEquipment(inputs, currentUser.getId());
                  case COMPUTER_HARDWARE ->
                      productController.addComputerHardware(inputs, currentUser.getId());
                };

              if (productCreated) {
                    System.out.println(inputs.get(0) + " créé avec succès.");
                } else {
                    System.out.println("Échec de la création du produit. Veuillez réessayer.");
                }
            }
        };
    }


}