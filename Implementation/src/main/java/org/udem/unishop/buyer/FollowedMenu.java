package org.udem.unishop.buyer;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.user.UserPage;
import java.util.UUID;

/**
 * The FollowedMenu class represents a menu for displaying and interacting with followed users by the current buyer.
 * It enables the user to view and navigate to the pages of users they are following.
 */
public class FollowedMenu {

  private final SubMenu followedMenu;

  private final Buyer currentUser;

  /**
   * Constructs a FollowedMenu instance for managing followed users.
   *
   * @param userController    The controller handling user-related operations.
   * @param productController The controller managing product-related functionalities.
   * @param currentUser       The current logged-in buyer user.
   */
  public FollowedMenu(UserController userController, ProductController productController, Buyer currentUser) {
    this.currentUser = currentUser;
    this.followedMenu = new SubMenu("Utilisateurs suivis");

    createFollowedList(userController, productController);
  }

  /**
   * Runs the FollowedMenu, executing interactions with followed users.
   */
  public void run() {
    followedMenu.execute();
  }

  /**
   * Creates the list of followed users and adds their profiles to the menu.
   *
   * @param userController    The controller handling user-related operations.
   * @param productController The controller managing product-related functionalities.
   */
  private void createFollowedList(UserController userController, ProductController productController) {
    for (UUID userId : currentUser.getFollowed()) {
      User user = userController.getUserById(userId);

      if (user != null) {
        UserPage userPage = new UserPage(user, productController,userController, currentUser);

        Command showUserCommand = new Command() {
          @Override
          public String getName() {
            return user.getUsername();
          }

          @Override
          public void execute() {
            userPage.run();
          }
        };

        followedMenu.addMenuComponent(new MenuItem(showUserCommand));
      }
    }
  }

}