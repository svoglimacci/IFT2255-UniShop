package org.udem.unishop.user;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;
import org.udem.unishop.models.UserList;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

public class UserCatalog {

  private final SubMenu userCatalog;

  private final User currentUser;

  private final AccountType accountType;

  public UserCatalog(UserController userController, ProductController productController,
      User currentUser, AccountType accountType) {
    this.currentUser = currentUser;
    this.accountType = accountType;
    this.userCatalog = new SubMenu("Catalogue d'utilisateurs");
    createUserList(productController, userController);

  }

  public void run() {
    userCatalog.execute();
  }

  private void createUserList(ProductController productController,
      UserController userController) {
    UserList userList = accountType == AccountType.BUYER ? userController
        .getBuyers() : userController.getSellers();

    for (Object object : userList) {
      User user = (User) object;

      UserPage userPage = new UserPage(user, productController, userController,
          currentUser);

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

      userCatalog.addMenuComponent(new MenuItem(showUserCommand));


    }
  }

}