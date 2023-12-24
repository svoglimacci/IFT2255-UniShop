package org.udem.unishop.user;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

/**
 * The UserMenu class represents a menu specific to a user, providing options such as liking or following the user.
 */
public class UserMenu extends SubMenu {

  private final User user;
  private final UserController userController;
  private final ProductController productController;
  private final User currentUser;

  /**
   * Constructor for UserMenu.
   *
   * @param user The user for whom the menu is created.
   * @param userController The UserController instance for user-related operations.
   * @param productController The ProductController instance for product-related operations.
   * @param currentUser The current user interacting with the menu.
   */
  public UserMenu(User user, UserController userController, ProductController productController,
      User currentUser) {
    super(user.getUsername());
    this.user = user;
    this.userController = userController;
    this.productController = productController;
    this.currentUser = currentUser;

    initializeMenu();
  }

  /**
   * Initializes the user menu by adding specific commands based on the user type.
   */
  private void initializeMenu() {

    if (currentUser instanceof Buyer) {
      if (user instanceof Seller) {
        Command likeCommand = createLikeCommand();
        addMenuComponent(new MenuItem(likeCommand));
      }
      if (user instanceof Buyer && !user.getId().equals(currentUser.getId())) {
        Command followCommand = createFollowCommand();
        addMenuComponent(new MenuItem(followCommand));
      }
    }
  }

  /**
   * Creates a command for following or unfollowing the user based on the current following status.
   *
   * @return The follow command.
   */
  private Command createFollowCommand() {
    final String[] commandName = {
        currentUser instanceof Buyer && ((Buyer) currentUser).getFollowed()
            .contains(user.getId()) ? "Unfollow" : "Follow"};
    return new Command() {
      @Override
      public String getName() {
        return commandName[0];
      }

      @Override
      public void execute() {
        if (commandName[0].equals("Follow")) {
          followBuyer();
          commandName[0] = "Unfollow";
        } else {
          unfollowBuyer();
          commandName[0] = "Follow";
        }
      }
    };
  }

  /**
   * Handles the action of following the user.
   */
  private void followBuyer() {

    Buyer buyer = (Buyer) currentUser;

    boolean followed = userController.addFollowed(buyer.getId(), user.getId());

    if (followed) {
      System.out.println("User followed!");

    } else {
      System.out.println("Failed to follow the user. Please try again.");
    }
  }

  /**
   * Handles the action of unfollowing the user.
   */
  private void unfollowBuyer() {

    Buyer buyer = (Buyer) currentUser;
    boolean unfollowed = userController.removeFollowed(buyer.getId(), user.getId());

    if (unfollowed) {
      System.out.println("User unfollowed!");

    } else {
      System.out.println("Failed to unfollow the user. Please try again.");
    }
  }


  /**
   * Creates a command for liking or unliking the user based on the current liking status.
   *
   * @return The like command.
   */
  private Command createLikeCommand() {
    final String[] commandName = {
        currentUser instanceof Buyer && ((Buyer) currentUser).getLikedSeller().contains(user.getId())
            ? "Unlike" : "Like"};
    return new Command() {
      @Override
      public String getName() {
        return commandName[0];
      }

      @Override
      public void execute() {
        if (commandName[0].equals("Like")) {
          likeSeller();
          commandName[0] = "Unlike";
        } else {
          unlikeSeller();
          commandName[0] = "Like";
        }
      }
    };
  }

  /**
   * Handles the action of liking the user.
   */
  private void likeSeller() {

    Buyer buyer = (Buyer) currentUser;
    boolean liked = userController.addUserLike(buyer.getId(), user.getId());

    if (liked) {
      System.out.println("User liked!");

    } else {
      System.out.println("Failed to like the user. Please try again.");
    }
  }

  /**
   * Handles the action of unliking the user.
   */
  private void unlikeSeller() {

    Buyer buyer = (Buyer) currentUser;
    boolean unliked = userController.removeUserLike(buyer.getId(), user.getId());

    if (unliked) {
      System.out.println("User unliked!");

    } else {
      System.out.println("Failed to unlike the user. Please try again.");
    }
  }
}