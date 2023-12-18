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


public class FollowedMenu {

  private final SubMenu followedMenu;

  private final Buyer currentUser;

  public FollowedMenu(UserController userController, ProductController productController, Buyer currentUser) {
    this.currentUser = currentUser;
    this.followedMenu = new SubMenu("Utilisateurs suivis");

    createFollowedList(userController, productController);
  }

  public void run() {
    followedMenu.execute();
  }

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