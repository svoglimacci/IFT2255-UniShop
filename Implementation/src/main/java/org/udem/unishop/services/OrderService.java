package org.udem.unishop.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Cart;
import org.udem.unishop.models.CartItem;
import org.udem.unishop.models.Issue;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.IssueState;
import org.udem.unishop.utilities.OrderState;


/**
 * Service class for handling order-related operations.
 */
public class OrderService {

    private final UserService userService;
    private final ProductService productService;

  /**
   * Constructor for OrderService class.
   *
   * @param userService The user service to interact with user-related operations.
   * @param productService The product service to interact with product-related operations.
   */
  public OrderService(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

  /**
   * Creates orders for the products included in the buyer's cart.
   * Handles various aspects such as updating user points, removing instances, and creating orders with associated
   * products.
   *
   * @param currentUser The buyer for whom the orders are being created.
   * @param usePoints A boolean indicating whether to use loyalty points for the order.
   * @return A list of unique order IDs generated during the process.
   */
  public List<UUID> createOrder(Buyer currentUser, boolean usePoints) {
    Cart cart = currentUser.getCart();
    Map<Seller, Map<UUID, Integer>> sellerProductsMap = new HashMap<>();
    List<UUID> orderIds = new ArrayList<>();

    for (CartItem cartItem : cart.getItems()) {
      Product product = productService.getProductById(cartItem.getId());
      Seller seller = (Seller) userService.getUserById(product.getSellerId());
      if (sellerProductsMap.containsKey(seller)) {
        Map<UUID, Integer> productsMap = sellerProductsMap.get(seller);
        productsMap.put(cartItem.getId(), cartItem.getQuantity());
      } else {
        Map<UUID, Integer> productsMap = new HashMap<>();
        productsMap.put(cartItem.getId(), cartItem.getQuantity());
        sellerProductsMap.put(seller, productsMap);
      }
    }

    for(Map.Entry<Seller, Map<UUID, Integer>> entry : sellerProductsMap.entrySet()) {

      // Calculate price
      double price = 0;
      for(Map.Entry<UUID, Integer> productEntry : entry.getValue().entrySet()) {
        Product product = productService.getProductById(productEntry.getKey());
        price += product.getPrice() * productEntry.getValue();
      }

      // create list of instances to remove
      List<UUID> instancesToRemove = new ArrayList<>();
      for(Map.Entry<UUID, Integer> productEntry : entry.getValue().entrySet()) {
        Product product = productService.getProductById(productEntry.getKey());
        for(int i = 0; i < productEntry.getValue(); i++) {
          instancesToRemove.add(product.getInstances().get(i));
        }
      }

      HashMap<UUID, List<UUID>> products = new HashMap<>();
      for(Map.Entry<UUID, Integer> productEntry : entry.getValue().entrySet()) {
        Product product = productService.getProductById(productEntry.getKey());
        List<UUID> instances = new ArrayList<>();
        for(int i = 0; i < productEntry.getValue(); i++) {
          instances.add(product.getInstances().get(i));
          product.removeInstance(product.getInstances().get(i));
        }
        productService.updateProduct(product);
        products.put(productEntry.getKey(), instances);
      }

    if(usePoints) {
      int points = currentUser.getFidelityPoints();
      double pointsToMoney = points * 0.02;

      if(pointsToMoney > price) {
        currentUser.setFidelityPoints(points - (int) (price / 0.02));
        price = 0;
      } else {
        currentUser.setFidelityPoints(0);
        price -= pointsToMoney;
      }
    } else {
      currentUser.addFidelityPoints(cart.getTotalPoints());
    }

    Order order = new Order(UUID.randomUUID(), currentUser.getId(), entry.getKey().getId(), products, price);
    orderIds.add(order.getId());
    userService.addOrderToUser(currentUser.getId(), order);
    userService.addOrderToUser(entry.getKey().getId(), order);
  }

  userService.clearCart(currentUser.getId());
  return orderIds;
  }

  /**
   * Modifies the status of an order based on the role of the current user.
   *
   * @param order The order to be modified.
   * @param currentUser The user initiating the status update.
   */
  public void modifyOrderStatus(Order order, User currentUser) {
      if (currentUser instanceof Seller) {
        Buyer buyer = (Buyer) userService.getUserById(order.getBuyerId());
        buyer.addNotification("Le vendeur " + currentUser.getUsername() + " a modifié le statut de la commande " + order.getId());
        if(order.getStatus() == OrderState.IN_PRODUCTION) {
          order.changeOrderStatus(OrderState.DELIVERING);
        }
        if(order.getStatus() == OrderState.AWAITING_RETURN) {
          order.changeOrderStatus(OrderState.RETURNED);
          for (Map.Entry<UUID, List<UUID>> entry : order.getProductsId().entrySet()) {
            Product product = productService.getProductById(entry.getKey());
            for (UUID instance : entry.getValue()) {
              product.addInstance(instance);
            }
            productService.updateProduct(product);
          }

        }
      } else {
        Seller seller = (Seller) userService.getUserById(order.getSellerId());
        seller.addNotification("L'acheteur " + currentUser.getUsername() + " a modifié le statut de la commande " + order.getId());
        order.getInstances().clear();
        order.changeOrderStatus(OrderState.DELIVERED);

      }

    userService.updateOrder(order, order.getStatus());


  }

  /**
   * Reports a problem related to an order, creating and associating an issue object with the order.
   *
   * @param order The reported order.
   * @param inputs A list of information about the issue.
   */
  public void reportProblem(Order order, List<String> inputs) {
    String description = inputs.get(0);
    order.setIssue(new Issue(description));

  }

  /**
   * Confirms the successful delivery of an order and updates the order status.
   *
   * @param order The order for which the delivery is confirmed.
   */
  public void confirmDelivery(Order order) {
      modifyOrderStatus(order, userService.getUserById(order.getSellerId()));
  }

  /**
   * Confirms the resolution of an issue related to delivery and updates the issue status.
   *
   * @param order The order associated with the resolved delivery issue.
   */
  public void confirmIssueDelivery(Order order) {
      order.getIssue().setStatus(IssueState.RESOLVED);
      order.getIssue().setSolution("Livraison confirmée");
      order.getIssue().setTrackingNumber(null);
      order.getIssue().setTrackingNumberDate(0);

  }

  /**
   * Confirms the initiation of a return process for an order and updates the issue status.
   *
   * @param order The order associated with the return process.
   */
  public void confirmIssueReturn(Order order) {
    order.getIssue().setStatus(IssueState.PROCESSING);
    order.getIssue().setTrackingNumber(UUID.randomUUID());

  }

  /**
   * Offers a solution to a reported issue on an order and updates the issue status.
   *
   * @param order The order associated with the reported issue.
   * @param s The offered solution to the reported issue.
   */
  public void offerSolution(Order order, String s) {
    order.getIssue().setSolution(s);
    order.getIssue().setTrackingNumber(UUID.randomUUID());
    order.getIssue().setTrackingNumberDate(System.currentTimeMillis());
    order.getIssue().setStatus(IssueState.AWAITING);
    Buyer buyer = (Buyer) userService.getUserById(order.getBuyerId());
    Seller seller = (Seller) userService.getUserById(order.getSellerId());
    buyer.addNotification("Le vendeur " + seller.getUsername() +
            " a proposé une solution à votre problème sur la commande " + order.getId());

  }

  /**
   * Accepts the proposed solution to a reported issue on an order and updating the issue status.
   *
   * @param order The order associated with the reported issue.
   */
  public void acceptSolution(Order order) {
    order.getIssue().setStatus(IssueState.AWAITING_RETURN);

  }

  /**
   * Cancels the return process for an order's reported issue and updates the issue status.
   *
   * @param order The order associated with the reported issue.
   */
  public void cancelIssueReturn(Order order) {
    order.getIssue().setStatus(IssueState.CLOSED);
    order.getIssue().setSolution("Délai de retour dépassé");
    order.getIssue().setTrackingNumber(null);
    order.getIssue().setTrackingNumberDate(0);

  }

  /**
   * Creates the return process for an order and updates order status.
   *
   * @param order The order for which the return process is initiated.
   */
  public void createReturnOrder(Order order) {
    order.changeOrderStatus(OrderState.AWAITING_RETURN);

    userService.updateOrder(order, order.getStatus());

  }

  /**
   * Processes the return of specific items from an order and updates the order status.
   *
   * @param order The order for which the return is processed.
   * @param returnItems A mapping of product IDs to lists of instances representing the items being returned.
   */
  public void returnOrder(Order order, HashMap<UUID, List<UUID>> returnItems) {
    Set<UUID> products = new HashSet<>();
        for(Map.Entry<UUID, List<UUID>> entry : returnItems.entrySet()) {
          Product product = productService.getProductById(entry.getKey());
          for(UUID instance : entry.getValue()) {
            product.addInstance(instance);
          }
        }
    order.changeOrderStatus(OrderState.AWAITING_RETURN);

    userService.updateOrder(order, order.getStatus());

  }

  /**
   * Processes the exchange of items in an order and updates order status.
   *
   * @param order The order for which the exchange is processed.
   * @param exchangeItems A mapping of product IDs to lists of instances representing the items being exchanged.
   * @param returnItems A mapping of product IDs to lists of instances representing the items being returned.
   */
  public void exchangeOrder(Order order, HashMap<UUID, List<UUID>> exchangeItems, HashMap<UUID, List<UUID>> returnItems) {
    Set<UUID> products = new HashSet<>();
    for(Map.Entry<UUID, List<UUID>> entry : returnItems.entrySet()) {
      Product product = productService.getProductById(entry.getKey());
      for(UUID instance : entry.getValue()) {
        product.addInstance(instance);
      }
    }
    for(Map.Entry<UUID, List<UUID>> entry : exchangeItems.entrySet()) {
      Product product = productService.getProductById(entry.getKey());
      for(UUID instance : entry.getValue()) {
        product.removeInstance(instance);
      }
    }
    order.changeOrderStatus(OrderState.AWAITING_EXCHANGE);

    userService.updateOrder(order, order.getStatus());
  }

  /**
   * Retrieves an order by its ID for a specific buyer.
   *
   * @param buyerId The ID of the buyer for whom the order is retrieved.
   * @param orderId The ID of the order to retrieve.
   * @return The order with the specified ID for the given buyer, or {@code null} if not found.
   */
  public Order getOrderById(UUID buyerId, UUID orderId) {
    Buyer buyer = (Buyer) userService.getUserById(buyerId);
    Order order = buyer.getOrderList().stream().filter(o -> o.getId().equals(orderId)).findFirst().orElse(null);
    return order;
  }
}