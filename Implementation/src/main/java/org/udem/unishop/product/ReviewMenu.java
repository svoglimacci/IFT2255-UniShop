package org.udem.unishop.product;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Review;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.models.Product;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;

/**
 * The ReviewMenu class represents a menu for managing reviews on a product.
 */
public class ReviewMenu extends SubMenu {

  private final Product product;
  private final Review review;
  private final UserController userController;
  private final ProductController productController;
  private final User currentUser;

    /**
     * Constructor for ReviewMenu with the specified product, review, user controller, product controller
     * and current user.
     *
     * @param product            The product associated with the review.
     * @param review             The review to be managed.
     * @param userController     The controller for managing user-related actions.
     * @param productController  The controller for managing product-related actions.
     * @param currentUser        The current user interacting with the menu.
     */
  public ReviewMenu(Product product, Review review, UserController userController, ProductController productController,
                    User currentUser) {
    super("");
    this.product = product;
    this.review = review;
    this.userController = userController;
    this.productController = productController;
    this.currentUser = currentUser;

    initializeMenu();
  }

    /**
     * Initializes the menu by adding relevant commands.
     */
  private void initializeMenu() {
    Command likeReviewCommand = createLikeReviewCommand();
    addMenuComponent(new MenuItem(likeReviewCommand));

  }
    /**
     * Creates a command for liking or unliking the review based on the current user's preference.
     *
     * @return The command for liking or unliking the review.
     */
      private Command createLikeReviewCommand() {
        final String[] commandName = {
            currentUser instanceof Buyer && ((Buyer) currentUser).getLikedReviews()
                .contains(review.getId()) ? "Retirer la mention j'aime" : "Ajouter une mention j'aime"};
        return new Command() {
            @Override
            public String getName() {
                return commandName[0];
            }

            @Override
            public void execute() {
                if (commandName[0].equals("Ajouter une mention j'aime")) {
                    likeReview();
                    commandName[0] = "Retirer la mention j'aime";
                } else {
                    unlikeReview();
                    commandName[0] = "Ajouter une mention j'aime";
                }
            }
        };
    }

    /**
     * Likes the review and provides feedback to the user.
     */
    private void likeReview() {
        boolean liked = productController.addReviewLike(currentUser.getId(), product.getId(), review.getId());

        if (liked) {
            System.out.println("Mention j'aime ajoutée avec succès!");

        } else {
            System.out.println("Impossible d'ajouter la mention j'aime. Veuillez réessayer.");
        }
    }

    /**
     * Unlikes the review and provides feedback to the user.
     */
    private void unlikeReview() {

        boolean unliked = productController.removeReviewLike(currentUser.getId(), product.getId(), review.getId());

        if (unliked) {
            System.out.println("Mention j'aime retirée avec succès.");

        } else {
            System.out.println("Impossible de retirer la mention j'aime. Veuillez réessayer.");
        }
    }


}