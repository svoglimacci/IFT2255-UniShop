package org.udem.unishop.common;

import java.util.List;
import java.util.Set;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.SubMenu;

/**
 * The NotificationsMenu class generates a menu displaying notifications for the current user.
 * It allows viewing and managing notifications related to orders and products.
 */
public class NotificationsMenu {

  private SubMenu notificationsMenu;

  private final UserController userController;

  private final ProductController productController;


  private final User currentUser;

  /**
   * Constructs a NotificationsMenu for displaying user notifications.
   *
   * @param userController    The controller for user-related functionalities.
   * @param productController The controller for product-related functionalities.
   * @param currentUser       The current user accessing the notifications menu.
   */
  public NotificationsMenu(UserController userController, ProductController productController, User currentUser) {
    this.userController = userController;
    this.productController = productController;
    this.currentUser = currentUser;

    this.notificationsMenu = new SubMenu("Mes notifications");
    createNotificationsList(productController, userController, currentUser);
  }

  /**
   * Runs the notifications menu to display and manage notifications.
   */
  public void run() {
    notificationsMenu.execute();
  }

  /**
   * Creates a list of notifications for the current user and displays them.
   * It limits the displayed notifications to the latest 5 entries for better readability.
   *
   * @param productController The controller for product-related functionalities.
   * @param userController    The controller for user-related functionalities.
   * @param currentUser       The current user accessing the notifications.
   */
  private void createNotificationsList(ProductController productController, UserController userController, User currentUser) {
    Set<String> notifications = currentUser.getNotifications();
    if (notifications.size() >= 5) {
      int i = 0;
      for (String notification : notifications) {
        if (i < notifications.size() - 5) {
          notifications.remove(notification);
        }
        i++;
      }
    }

    if(notifications.isEmpty()){
      System.out.println("Vous n'avez aucune notification");
    }
    else{
      for (String notification : currentUser.getNotifications()) {
        System.out.println(notification);
      }
    }
  }



}