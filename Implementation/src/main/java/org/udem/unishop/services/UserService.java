package org.udem.unishop.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Cart;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.models.UserList;
import org.udem.unishop.repositories.UserRepository;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.OrderState;

/**
 * Service class for handling user-related operations.
 */
public class UserService {

  private final UserRepository userRepository;

  /**
   * Constructs a UserService class with a specified UserRepository.
   *
   * @param userRepository The repository for managing user-related data.
   */
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  /**
   * Attempts to log in a user with the specified credentials and account type.
   *
   * @param username The username of the user attempting to log in.
   * @param password The password associated with the username.
   * @param accountType The type of account for which the login is attempted.
   * @return The logged-in user if successful, null otherwise.
   */
  public User login(String username, String password, AccountType accountType) {
    User user = userRepository.findByUsername(username, accountType);
    if (user != null) {
      if (userMatchesRole(user, username, password, accountType)) {
        if (!user.getStatus()) {
          if (isWithin24Hours(user.getCreationDate())) {
            user.setStatus(true);
            userRepository.update(user);
          } else {
            userRepository.delete(user);
            return null;

          }
        }
        return user;
      }
    }
    return null;
  }

  /**
   * Checks if the provided user matches the specified username, password and account type.
   *
   * @param user The user to be checked.
   * @param username The username to compare.
   * @param password The password to compare.
   * @param accountType The account type to compare.
   * @return true if the user matches the specified credentials and account type, false otherwise.
   */
  private boolean userMatchesRole(User user, String username, String password,
      AccountType accountType) {
    return user.getAccountType().equals(accountType) && user.getUsername().equals(username)
        && user.getPassword().equals(password);
  }

  /**
   * Checks if the provided date is within the last 24 hours from the current time.
   *
   * @param creationDate The date to be checked.
   * @return true if the date is within the last 24 hours, false otherwise.
   */
  private boolean isWithin24Hours(Date creationDate) {
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - creationDate.getTime();
    return elapsedTime <= 24 * 60 * 60 * 1000;
  }

  /**
   * Gets a user by their unique identifier.
   *
   * @param userId The unique identifier of the user.
   * @return The user corresponding to the provided identifier.
   */
  public User getUserById(UUID userId) {
    return userRepository.findById(userId);
  }

  /**
   * Gets a user by their username and account type.
   *
   * @param username The username of the user.
   * @param accountType The account type of the user.
   * @return The user corresponding to the provided username and account type.
   */
  public User getUserByUsername(String username, AccountType accountType) {
    return userRepository.findByUsername(username, accountType);
  }

  /**
   * Creates a new buyer with the provided inputs and saves it to the user repository.
   *
   * @param inputs A list of string inputs containing information for creating a buyer.
   * @return true if the buyer is successfully created and saved, false otherwise.
   */
  public boolean createBuyer(List<String> inputs) {
    Buyer newBuyer = new Buyer(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3),
        inputs.get(4), inputs.get(5), inputs.get(6));
    return userRepository.save(newBuyer);

  }

  /**
   * Creates a new seller with the provided inputs and saves it to the user repository.
   *
   * @param inputs A list of string inputs containing information for creating a seller.
   * @return true if the seller is successfully created and saved, false otherwise.
   */
  public boolean createSeller(List<String> inputs) {
    Seller newSeller = new Seller(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3),
        inputs.get(4), inputs.get(5));
    return userRepository.save(newSeller);
  }

  /**
   * Adds a product to the list of liked products for the specified buyer.
   *
   * @param userId The unique identifier of the buyer.
   * @param productId The unique identifier of the product to be added to the liked products list.
   * @return true if the product is successfully added to the liked products list, false otherwise.
   */
  public boolean addLikedProduct(UUID userId, UUID productId) {
    Buyer user = (Buyer) getUserById(userId);
    user.getLikedProducts().add(productId);

    return userRepository.update(user);
  }

  /**
   * Removes a product from the list of liked products for the specified buyer.
   *
   * @param userId The unique identifier of the buyer.
   * @param productId The unique identifier of the product to be removed from the liked products list.
   * @return true if the product is successfully removed from the liked products list, false otherwise.
   */
  public boolean removeLikedProduct(UUID userId, UUID productId) {
    Buyer user = (Buyer) getUserById(userId);
    user.getLikedProducts().remove(productId);
    return userRepository.update(user);
  }

  /**
   * Adds a product to the list of products associated with the specified seller user.
   *
   * @param user The seller user to whom the product is being added.
   * @param productId The unique identifier of the product to be added to the seller's products list.
   * @return true if the product is successfully added to the seller's products list, false otherwise.
   */
  public boolean addProductToSeller(User user, UUID productId) {
    user.getProductsIds().add(productId);
    UserList users = userRepository.getBuyers();
    for (Object buyer : users) {
      Buyer buyerUser = (Buyer) buyer;
      if (buyerUser.getLikedSeller().contains(user.getId())) {
        buyerUser.addNotification("Un produit que vous aimez est maintenant disponible: " + productId);
        userRepository.update(buyerUser);
      }
    }


    return userRepository.update(user);
  }

  /**
   * Gets a list of all users in the system.
   *
   * @return A UserList containing all users in the system.
   */
  public UserList getUsers() {
    return userRepository.getUsers();
  }

  /**
   * Gets a list of all buyers in the system.
   *
   * @return A BuyerList containing all users in the system.
   */
  public UserList getBuyers() {
    return userRepository.getBuyers();
  }

  /**
   * Gets a list of all Sellers in the system.
   *
   * @return A SellerList containing all users in the system.
   */
  public UserList getSellers() {
    return userRepository.getSellers();
  }

  /**
   * Adds a like from a buyer to a seller, increasing the seller's like count and updating the buyer's liked sellers.
   *
   * @param buyerId The UUID of the buyer.
   * @param sellerId The UUID of the seller.
   * @return true if the like was successfully added, false otherwise
   */
  public boolean addUserLike(UUID buyerId, UUID sellerId) {
    Buyer buyer = (Buyer) getUserById(buyerId);
    Seller seller = (Seller) getUserById(sellerId);

    if (buyer == null || seller == null) {
      return false;
    }

    if (buyer.getLikedSeller().contains(sellerId)) {
      return false;
    }

    seller.setLikes(seller.getLikes() + 1);
    buyer.getLikedSeller().add(sellerId);
    return userRepository.update(seller);
  }

  /**
   * Removes a like from a buyer to a seller, decreasing the seller's like count and updating the buyer's liked sellers.
   *
   * @param buyerId The UUID of the buyer.
   * @param sellerId The UUID of the seller.
   * @return true if the like was successfully removed, false otherwise
   */
  public boolean removeUserLike(UUID buyerId, UUID sellerId) {
    Buyer buyer = (Buyer) getUserById(buyerId);
    Seller seller = (Seller) getUserById(sellerId);

    if (buyer == null || seller == null) {
      return false;
    }
    if (!buyer.getLikedSeller().contains(sellerId)) {
      return false;
    }
    seller.setLikes(seller.getLikes() - 1);
    buyer.getLikedSeller().remove(sellerId);
    return userRepository.update(seller);
  }

  /**
   * Adds a review to the list of reviews associated with a buyer.
   *
   * @param buyerId The UUID of the buyer.
   * @param reviewId The UUID of the review to be added.
   */
  public void addReviewToBuyer(UUID buyerId, UUID reviewId) {
    Buyer buyer = (Buyer) getUserById(buyerId);
    buyer.getReviews().add(reviewId);
    userRepository.update(buyer);
  }

  /**
   * Removes a cart item from the shopping cart of the current buyer and updates the user information.
   *
   * @param currentUser The buyer whose shopping cart is being modified.
   * @param cartItem The cart item to be removed from the shopping cart.
   */
  public void removeItemFromCart(Buyer currentUser, CartItem cartItem) {
    currentUser.getCart().removeItem(cartItem);
    userRepository.update(currentUser);
  }

  /**
   * Adds a specified quantity of a product to the shopping cart of the current user.
   *
   * @param currentUser The user whose shopping cart is being modified.
   * @param product The product to be added to the shopping cart.
   * @param quantity The quantity of the product to be added.
   * @return true if the product is successfully added to the cart, false otherwise.
   */
  public boolean addItemToCart(User currentUser, Product product, int quantity) {
    if (currentUser instanceof Buyer buyer) {
      Cart cart = buyer.getCart();

      // Check if the product is already in the cart
      Optional<CartItem> existingItem = cart.getItems().stream().filter(cartItem -> cartItem.getId().equals(product.getId())).findFirst();

      if (existingItem.isPresent()) {
        // If the product is already in the cart, update its quantity
        CartItem cartItem = existingItem.get();
        if (cartItem.getQuantity() + quantity <= product.getQuantity()) {
          cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
          return false;
        }
      } else {
        // If the product is not in the cart, add a new CartItem
        CartItem newCartItem = new CartItem(product.getId(), product.getName(), quantity, product.getPrice(), product.getBonusPoints());
        cart.getItems().add(newCartItem);
      }

      userRepository.update(buyer);

      return true;
    }

    return false;
  }

  /**
   * Sets a new password for the user with the specified ID.
   *
   * @param id The unique identifier of the user.
   * @param input The new password to be set.
   */
  public void setPassword(UUID id, String input) {
    User user = getUserById(id);
    user.setPassword(input);
    userRepository.update(user);
  }

  /**
   * Sets a new email address for the user with the specified ID.
   *
   * @param id The unique identifier of the user.
   * @param input The new email address to be set.
   */
  public void setEmail(UUID id, String input) {
    User user = getUserById(id);
    user.setEmail(input);
    userRepository.update(user);
  }

  /**
   * Sets a new email address for the user with the specified ID.
   *
   * @param id The unique identifier of the user.
   * @param input The new email address to be set.
   */
  public void setAddress(UUID id, String input) {
    User user = getUserById(id);
    user.setAddress(input);
    userRepository.update(user);
  }

  /**
   * Sets a new phone number for the user with the specified ID.
   *
   * @param id The unique identifier of the user.
   * @param input The new phone number to be set.
   */
  public void setPhoneNumber(UUID id, String input) {
    User user = getUserById(id);
    user.setPhoneNumber(input);
    userRepository.update(user);
  }

  /**
   * Adds a user with the specified ID to the list of users being followed by another user and awards 5 fidelity points
   * to both users.
   *
   * @param buyer The unique identifier of the buyer user.
   * @param followed The unique identifier of the user being followed.
   * @return true if the user was successfully added to the list of followed users,
   *         false if the user was already in the list.
   */
  public boolean addFollowed(UUID buyer, UUID followed) {
    Buyer buyerUser = (Buyer) getUserById(buyer);
    Buyer followedUser = (Buyer) getUserById(followed);

    //add 5 points to both users
    buyerUser.addFidelityPoints(5);
    followedUser.addFidelityPoints(5);

    if (buyerUser.getFollowed().contains(followed)) {
      return false;
    }

    buyerUser.getFollowed().add(followed);
    followedUser.getFollowers().add(buyer);
    followedUser.addNotification("Vous avez un nouveau follower: " + buyerUser.getUsername());
    return userRepository.update(followedUser);
  }

  /**
   * Removes a user with the specified ID from the list of users being followed by another user and  deducts 5 fidelity
   * points from both users.
   *
   * @param buyer The unique identifier of the buyer user.
   * @param followed The unique identifier of the user being unfollowed.
   * @return true if the user was successfully removed from the list of followed users,
   *         false if the user was not in the list.
   */
  public boolean removeFollowed(UUID buyer, UUID followed) {
    Buyer buyerUser = (Buyer) getUserById(buyer);
    Buyer followedUser = (Buyer) getUserById(followed);

    //remove 5 points to both users
    buyerUser.removeFidelityPoints(5);
    followedUser.removeFidelityPoints(5);

    if (!buyerUser.getFollowed().contains(followed)) {
      return false;
    }

    buyerUser.getFollowed().remove(followed);
    followedUser.getFollowers().remove(buyer);
    return userRepository.update(followedUser);
  }

  /**
   * Adds a new order to the list of orders associated with a user identified by the provided ID.
   *
   * @param id The unique identifier of the user.
   * @param order The order to be added to the user's list of orders.
   */
  public void addOrderToUser(UUID id, Order id1) {
    User user = getUserById(id);
    user.getOrderList().add(id1);
    userRepository.update(user);
  }

  /**
   * Removes an order with the specified order ID from the list of orders associated with a user identified by the provided ID.
   *
   * @param id The unique identifier of the user.
   * @param orderId The unique identifier of the order to be removed from the user's list of orders.
   */
  public void removeOrderFromUser(UUID id, UUID orderId) {
    User user = getUserById(id);
    user.removeOrder(orderId);
    userRepository.update(user);
  }

  /**
   * Clears the shopping cart of a buyer identified by the provided user ID.
   *
   * @param id The unique identifier of the buyer.
   */
  public void clearCart(UUID id) {
    Buyer user = (Buyer) getUserById(id);
    user.getCart().getItems().clear();
    userRepository.update(user);
  }

  /**
   * Updates the state of the specified order for both the seller and the buyer.
   *
   * @param order The order to be updated.
   * @param orderState The new state of the order.
   */
  public void updateOrder(Order order, OrderState orderState) {
    Seller seller = (Seller) getUserById(order.getSellerId());
    Buyer buyer = (Buyer) getUserById(order.getBuyerId());

    seller.updateOrder(order.getId(), orderState);
    buyer.updateOrder(order.getId(), orderState);

    userRepository.update(seller);
    userRepository.update(buyer);
  }

  /**
   * Updates the information of the specified user in the user repository.
   *
   * @param currentUser The user whose information needs to be updated.
   */
  public void updateUser(User currentUser) {
    userRepository.update(currentUser);
  }

}