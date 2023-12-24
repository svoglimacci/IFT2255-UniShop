package org.udem.unishop.controllers;

import java.util.List;
import java.util.UUID;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Cart;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.User;
import org.udem.unishop.models.UserList;
import org.udem.unishop.services.UserService;
import org.udem.unishop.utilities.AccountType;

/**
 * Controller class managing operations related to users.
 */
public class UserController {

  private final UserService userService;

  /**
   * Constructs a UserController object.
   *
   * @param userService The service handling user-related operations.
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  public User login(List<String> inputs, AccountType accountType) {
    String username = inputs.get(0);
    String password = inputs.get(1);

    return userService.login(username, password, accountType);
  }

  public User getUserById(UUID userId) {

    return userService.getUserById(userId);
  }

  public User getUserByUsername(String username, AccountType accountType) {
    return userService.getUserByUsername(username, accountType);
  }

  public boolean register(List<String> inputs, AccountType accountType) {
    if (accountType == AccountType.BUYER) {
      return userService.createBuyer(inputs);
    } else {
      return userService.createSeller(inputs);
    }
  }


  public UserList getUsers() {
    return userService.getUsers();
  }

  public UserList getBuyers() {
    return userService.getBuyers();
  }

  public UserList getSellers() {
    return userService.getSellers();
  }

  public boolean addUserLike(UUID buyerId, UUID sellerId) {
    return userService.addUserLike(buyerId, sellerId);

  }

  public boolean removeUserLike(UUID buyerId, UUID sellerId) {
    return userService.removeUserLike(buyerId, sellerId);
  }

  public void removeItemFromCart(Buyer currentUser, CartItem cartItem) {
    userService.removeItemFromCart(currentUser, cartItem);
  }

  public boolean addItemToCart(User currentUser, Product product, String input) {
    int quantity = Integer.parseInt(input);
    return userService.addItemToCart(currentUser, product, quantity);
  }

  public void setPassword(UUID id, String input) {
    userService.setPassword(id, input);
  }

  public void setEmail(UUID id, String input) {
    userService.setEmail(id, input);
  }

  public void setAddress(UUID id, String input) {
    userService.setAddress(id, input);
  }

  public void setPhoneNumber(UUID id, String input) {
    userService.setPhoneNumber(id, input);
  }

  public boolean addFollowed(UUID buyer, UUID followed) {
    return userService.addFollowed(buyer, followed);
  }

  public boolean removeFollowed(UUID buyer, UUID followed) {
    return userService.removeFollowed(buyer, followed);
  }


  public void updateUser(User currentUser) {
    userService.updateUser(currentUser);
  }
}