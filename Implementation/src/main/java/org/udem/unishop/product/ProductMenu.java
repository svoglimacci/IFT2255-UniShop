package org.udem.unishop.product;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Review;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.models.User;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.SubMenu;


public class ProductMenu extends SubMenu {

    private final Product product;
    private final UserController userController;
    private final ProductController productController;
    private final User currentUser;

    public ProductMenu(Product product, UserController userController, ProductController productController, User currentUser) {
        super(product.getName());
        this.product = product;
        this.userController = userController;
        this.productController = productController;
        this.currentUser = currentUser;

        initializeMenu();
    }

    private void initializeMenu() {

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
                    System.out.println("Product added to cart!");

                } else {
                    System.out.println("Failed to add product to cart. Please try again.");
                }
            }
        };
    }


    private boolean hasBuyerReviewedProduct(Buyer buyer, Product product) {
    List<UUID> buyerReviews = buyer.getReviews();

    return buyerReviews.stream()
            .anyMatch(reviewId ->
                    product.getReviews().stream()
                            .anyMatch(productReview -> productReview.getId().equals(reviewId))
            );
}


    private Command createLikeCommand() {
        final String[] commandName = {
            currentUser instanceof Buyer && ((Buyer) currentUser).getLikedProducts()
                .contains(product.getId()) ? "Unlike" : "Like"};
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

    private Command createAddReviewCommand() {
        Prompt reviewPrompt = new ReviewPrompt().createReviewPrompt();

        return new Command() {
            @Override
            public String getName() {
                return "Add review";
            }

            @Override
            public void execute() {
                List<String> inputs = reviewPrompt.getValuesFromUser();
                boolean reviewAdded = productController.addReview(inputs, (Buyer) currentUser, product);

                if (reviewAdded) {
                    System.out.println("Review added!");
                } else {
                    System.out.println("Failed to add review. Please try again.");
                }

            }
        };


    }


   private void likeProduct() {


    boolean liked = productController.addProductLike(currentUser.getId(), product.getId());

    if (liked) {
        System.out.println("Product liked!");

    } else {
        System.out.println("Failed to like the product. Please try again.");
    }
}

private void unlikeProduct() {

    boolean unliked = productController.removeProductLike(currentUser.getId(), product.getId());

    if (unliked) {
        System.out.println("Product unliked!");

    } else {
        System.out.println("Failed to unlike the product. Please try again.");
    }
}
}