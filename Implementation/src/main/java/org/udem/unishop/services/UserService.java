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

public class UserService {

  private final UserRepository userRepository;


  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

  }


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

  private boolean userMatchesRole(User user, String username, String password,
      AccountType accountType) {
    return user.getAccountType().equals(accountType) && user.getUsername().equals(username)
        && user.getPassword().equals(password);
  }

  private boolean isWithin24Hours(Date creationDate) {
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - creationDate.getTime();
    return elapsedTime <= 24 * 60 * 60 * 1000;
  }

  public User getUserById(UUID userId) {
    return userRepository.findById(userId);
  }

  public User getUserByUsername(String username, AccountType accountType) {
    return userRepository.findByUsername(username, accountType);
  }


  public boolean createBuyer(List<String> inputs) {
    Buyer newBuyer = new Buyer(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3),
        inputs.get(4), inputs.get(5), inputs.get(6));
    return userRepository.save(newBuyer);

  }

  public boolean createSeller(List<String> inputs) {
    Seller newSeller = new Seller(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3),
        inputs.get(4), inputs.get(5));
    return userRepository.save(newSeller);
  }

  public boolean addLikedProduct(UUID userId, UUID productId) {
    Buyer user = (Buyer) getUserById(userId);
    user.getLikedProducts().add(productId);
    return userRepository.update(user);
  }

  public boolean removeLikedProduct(UUID userId, UUID productId) {
    Buyer user = (Buyer) getUserById(userId);
    user.getLikedProducts().remove(productId);
    return userRepository.update(user);
  }

  public boolean addProductToSeller(User user, UUID productId) {
    user.getProductsIds().add(productId);
    return userRepository.update(user);
  }

  public UserList getUsers() {
    return userRepository.getUsers();
  }

  public UserList getBuyers() {
    return userRepository.getBuyers();
  }

  public UserList getSellers() {
    return userRepository.getSellers();
  }

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

  public void addReviewToBuyer(UUID buyerId, UUID reviewId) {
    Buyer buyer = (Buyer) getUserById(buyerId);
    buyer.getReviews().add(reviewId);
    userRepository.update(buyer);
  }

  public void removeItemFromCart(Buyer currentUser, CartItem cartItem) {
    currentUser.getCart().removeItem(cartItem);
    userRepository.update(currentUser);
  }

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
        CartItem newCartItem = new CartItem(product.getId(), product.getName(), quantity, product.getPrice());
        cart.getItems().add(newCartItem);
      }

      userRepository.update(buyer);

      return true;
    }

    return false;
  }

  public void setPassword(UUID id, String input) {
    User user = getUserById(id);
    user.setPassword(input);
    userRepository.update(user);
  }

  public void setEmail(UUID id, String input) {
    User user = getUserById(id);
    user.setEmail(input);
    userRepository.update(user);
  }

  public void setAddress(UUID id, String input) {
    User user = getUserById(id);
    user.setAddress(input);
    userRepository.update(user);
  }

  public void setPhoneNumber(UUID id, String input) {
    User user = getUserById(id);
    user.setPhoneNumber(input);
    userRepository.update(user);
  }

  public boolean addFollowed(UUID buyer, UUID followed) {
    Buyer buyerUser = (Buyer) getUserById(buyer);
    Buyer followedUser = (Buyer) getUserById(followed);

    if (buyerUser == null || followedUser == null) {
      return false;
    }

    if (buyerUser.getFollowed().contains(followed)) {
      return false;
    }

    buyerUser.getFollowed().add(followed);
    followedUser.getFollowers().add(buyer);
    return userRepository.update(followedUser);
  }

  public boolean removeFollowed(UUID buyer, UUID followed) {
    Buyer buyerUser = (Buyer) getUserById(buyer);
    Buyer followedUser = (Buyer) getUserById(followed);

    if (buyerUser == null || followedUser == null) {
      return false;
    }

    if (!buyerUser.getFollowed().contains(followed)) {
      return false;
    }

    buyerUser.getFollowed().remove(followed);
    followedUser.getFollowers().remove(buyer);
    return userRepository.update(followedUser);
  }

  public void addOrderToUser(UUID id, Order id1) {
    User user = getUserById(id);
    user.getOrderList().add(id1);
    userRepository.update(user);
  }

  public void removeOrderFromUser(UUID id, UUID orderId) {
    User user = getUserById(id);
    user.removeOrder(orderId);
    userRepository.update(user);
  }

  public void clearCart(UUID id) {
    Buyer user = (Buyer) getUserById(id);
    user.getCart().getItems().clear();
    userRepository.update(user);
  }

  public void updateOrder(Order order, OrderState orderState) {
    Seller seller = (Seller) getUserById(order.getSellerId());
    Buyer buyer = (Buyer) getUserById(order.getBuyerId());

    seller.updateOrder(order.getId(), orderState);
    buyer.updateOrder(order.getId(), orderState);

    userRepository.update(seller);
    userRepository.update(buyer);
  }
}