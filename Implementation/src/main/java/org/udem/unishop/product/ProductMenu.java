package org.udem.unishop.product;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Review;
import org.udem.unishop.models.Seller;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.models.User;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.SubMenu;

/**
 * The ProductMenu class represents a menu for managing product-related actions.
 */
public class ProductMenu extends SubMenu {

    private final Product product;
    private final UserController userController;
    private final ProductController productController;
    private final User currentUser;

    /**
     * Constructor for ProductMenu with the specified product, user controller, product controller, and current user.
     *
     * @param product The product associated with the menu.
     * @param userController The controller for managing user-related actions.
     * @param productController The controller for managing product-related actions.
     * @param currentUser The current user interacting with the menu.
     */
    public ProductMenu(Product product, UserController userController, ProductController productController, User currentUser) {
        super(product.getName());
        this.product = product;
        this.userController = userController;
        this.productController = productController;
        this.currentUser = currentUser;

        initializeMenu();
    }

    /**
     * Initializes the menu by adding relevant commands based on the user type.
     */
    private void initializeMenu() {

      if(currentUser instanceof Seller) {
        Command addPromotionCommand = createAddPromotionCommand();
        Command changePointsCommand = createChangePointsCommand();
        addMenuComponent(new MenuItem(addPromotionCommand));
        addMenuComponent(new MenuItem(changePointsCommand));

      }

        if (currentUser instanceof Buyer) {
            Command likeCommand = createLikeCommand();
            Command addReviewCommand = createAddReviewCommand();
            Command addToCartCommand = createAddToCartCommand();
            addMenuComponent(new MenuItem(likeCommand));
            addMenuComponent(new MenuItem(addToCartCommand));

            if (!hasBuyerReviewedProduct((Buyer) currentUser, product)) {
                addMenuComponent(new MenuItem(addReviewCommand));
            }
        }
    }

    /**
     * Creates a command for changing the bonus points of the product.
     *
     * @return The command for changing the bonus points.
     */
    private Command createChangePointsCommand() {

      return new Command() {
            @Override
            public String getName() {
                return "Changer les points bonus";
            }

            @Override
            public void execute() {
                Prompt pointsPrompt = new PointsPrompt(product).createPointsPrompt();
                String input = pointsPrompt.getValuesFromUser().get(0);
                boolean changed = productController.changePoints(product, input);

                if (changed) {
                    System.out.println("Points bonus changés avec succès!");

                } else {
                    System.out.println("Impossible de changer les points bonus. Veuillez réessayer.");
                }
            }
        };
    }

    /**
     * Creates a command for adding a promotion to the product.
     *
     * @return The command for adding a promotion.
     */
    private Command createAddPromotionCommand() {

      return new Command() {
            @Override
            public String getName() {
                return "Ajouter une promotion";
            }

            @Override
            public void execute() {
                Prompt promotionPrompt = new PromotionPrompt().createPromotionPrompt();
                List<String> inputs = promotionPrompt.getValuesFromUser();
                boolean added = productController.addPromotion(product, inputs);

                if (added) {
                    System.out.println("Promotion ajoutée avec succès!");

                } else {
                    System.out.println("Impossible d'ajouter la promotion. Veuillez réessayer.");
                }
            }
        };
  }

    /**
     * Creates a command for adding the product to the cart.
     *
     * @return The command for adding the product to the cart.
     */
    private Command createAddToCartCommand() {
        return new Command() {
            @Override
            public String getName() {
                return "Ajouter au panier";
            }

            @Override
            public void execute() {
                Prompt quantityPrompt = new QuantityPrompt().createQuantityPrompt();
                String input = quantityPrompt.getValuesFromUser().get(0);
                boolean added = userController.addItemToCart(currentUser, product, input);

                if (added) {
                    System.out.println("Produit ajouté au panier avec succès!");

                } else {
                    System.out.println("Impossible d'ajouter le produit au panier. Veuillez réessayer.");
                }
            }
        };
    }

    /**
     * Checks if the buyer has already reviewed the product.
     *
     * @param buyer The buyer to check.
     * @param product The product to check for reviews.
     * @return True if the buyer has reviewed the product, false otherwise.
     */
    private boolean hasBuyerReviewedProduct(Buyer buyer, Product product) {
        List<UUID> buyerReviews = buyer.getReviews();

        return buyerReviews.stream()
                .anyMatch(reviewId ->
                        product.getReviews().stream()
                                .anyMatch(productReview -> productReview.getId().equals(reviewId))
                );
    }

    /**
     * Creates a command for liking or unliking the product based on the current user's preference.
     *
     * @return The command for liking or unliking the product.
     */
    private Command createLikeCommand() {
        final String[] commandName = {
            currentUser instanceof Buyer && ((Buyer) currentUser).getLikedProducts()
                .contains(product.getId()) ? "Retirer la mention j'aime" : "Ajouter une mention j'aime"};
        return new Command() {
            @Override
            public String getName() {
                return commandName[0];
            }

            @Override
            public void execute() {
                if (commandName[0].equals("Like")) {
                    likeProduct();
                    commandName[0] = "Unlike";
                } else {
                    unlikeProduct();
                    commandName[0] = "Like";
                }
            }
        };
    }

    /**
     * Creates a command for adding a review to the product.
     *
     * @return The command for adding a review.
     */
    private Command createAddReviewCommand() {
        Prompt reviewPrompt = new ReviewPrompt().createReviewPrompt();

        return new Command() {
            @Override
            public String getName() {
                return "Ajouter une évaluation";
            }

            @Override
            public void execute() {
                List<String> inputs = reviewPrompt.getValuesFromUser();
                boolean reviewAdded = productController.addReview(inputs, (Buyer) currentUser, product);

                if (reviewAdded) {
                    System.out.println("Évaluation ajoutée avec succès!");
                } else {
                    System.out.println("Impossible d'ajouter l'évaluation. Veuillez réessayer.");
                }

            }
        };


    }

    /**
     * Likes the product and provides feedback to the user.
     */
   private void likeProduct() {
        boolean liked = productController.addProductLike(currentUser.getId(), product.getId());

        if (liked) {
            System.out.println("Mention j'aime ajoutée avec succès!");

        } else {
            System.out.println("Impossible d'ajouter la mention j'aime. Veuillez réessayer.");
        }
    }

    /**
     * Unlikes the product and provides feedback to the user.
     */
    private void unlikeProduct() {

        boolean unliked = productController.removeProductLike(currentUser.getId(), product.getId());

        if (unliked) {
            System.out.println("Mention j'aime retirée avec succès.");

        } else {
            System.out.println("Impossible de retirer la mention j'aime. Veuillez réessayer.");
        }
    }
}