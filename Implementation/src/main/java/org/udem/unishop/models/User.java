package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.OrderState;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "account_type")
@JsonSubTypes({@JsonSubTypes.Type(value = Buyer.class, name = "buyer"),
    @JsonSubTypes.Type(value = Seller.class, name = "seller")})

/**
 * Represents a user in the system.
 */
public class User {

    @JsonProperty("order_list")
  private List<Order> orderList = new ArrayList<>();

  @JsonIgnore
  private final AccountType accountType = AccountType.valueOf(
      this.getClass().getSimpleName().toUpperCase());

  @JsonProperty("id")
  private UUID id;
  @JsonProperty("username")
  private String username;
  @JsonProperty("password")
  private String password;

  @JsonProperty("email")
  private String email;

  @JsonProperty("phone_number")
  private String phoneNumber;

  @JsonProperty("address")
  private String address;

    @JsonProperty("liked_reviews")
  private List<UUID> likedReviews = new ArrayList<>();

  @JsonProperty("creation_date")
  private Date creationDate = new Date();

  @JsonProperty("status")
  private boolean status = false;

  @JsonProperty("products_ids")
  private Set<UUID> productsIds = new HashSet<>();

  @JsonProperty("notifications")
  private Set<String> notifications = new HashSet<>();

  @JsonProperty("selected_metrics")
  private HashMap<String, List<String>> selectedMetrics = new HashMap<>();


  /**
   * Constructs a User object.
   *
   * @param username The username of the user.
   * @param password The password asscoaited with the user's account.
   * @param email The email of the user.
   * @param phoneNumber The phone number of the user.
   * @param address The physical address of the user.
   */
  @JsonCreator
  public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
      @JsonProperty("email") String email, @JsonProperty("phone_number") String phoneNumber,
      @JsonProperty("address") String address) {
    this.id = (id != null) ? id : UUID.randomUUID();
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  /**
   * Gets the unique identifier of the user.
   *
   * @return the unique identifier of the user.
   */
  public UUID getId() {
    return id;
  }

  /** Sets the unique identifier of the user.
   *
   * @param id The unique identifier to be set.
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Gets the username of the user.
   *
   * @return the username of the user.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the user.
   *
   * @param username The username to be set.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password associated with the user's account.
   *
   * @return the password associated with the user's account.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password associated with the user's account.
   *
   * @param password The password to be set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the email of the user.
   *
   * @return the email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the user.
   *
   * @param email The email to be set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the phone number of the user.
   *
   * @return The phone number of the user.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phone number of the user.
   *
   * @param phoneNumber The phone number to be set.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets the address of the user.
   *
   * @return The address of the user.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address of the user.
   *
   * @param address The address to be set.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets the account type of the user.
   *
   * @return the account type of the user.
   */
  public AccountType getAccountType() {
    return accountType;
  }

  /**
   * Gets the creation date of the user's account.
   *
   * @return The creation date of the user's account.
   */
  public Date getCreationDate() {
    return creationDate;
  }

  /**
   * Gets the status of the user's account.
   *
   * @return the status of the user's account.
   */
  public boolean getStatus() {
    return status;
  }

  /**
   * Sets the status of the user's account.
   *
   * @param status The status to be set.
   */
  public void setStatus(boolean status) {
    this.status = status;
  }

  /**
   * Gets the set of product IDs associated with the user.
   *
   * @return the set of product IDs associated with the user.
   */
  public Set<UUID> getProductsIds() {
    return productsIds;
  }

  /**
   * Sets the set of product IDs associated with the user.
   *
   * @param productsIds The set of product IDs to be set.
   */
  public void setProductsIds(Set<UUID> productsIds) {
    this.productsIds = productsIds;
  }

  /**
   * Adds a product ID to the set of product IDs associated with the user.
   * @param productId the product ID to be added.
   */
  public void addProductId(UUID productId) {
    this.productsIds.add(productId);
  }

  /**
   * Adds an order to the user's list of orders.
   *
   * @param order The order to be added.
   */
  public void addOrder(Order order) {
        orderList.add(order);
    }

  /**
   * Gets the list of orders associated with the user.
   *
   * @return the list of orders associated with the user.
   */
  public List<Order> getOrderList() {
        return orderList;
    }

  /**
   * Sets the list of orders associated with the user.
   *
   * @param orderList The list of orders to be set.
   */
  public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


  /**
   * Updates properties of an order.
   *
   * @param orderId The order ID to be updated.
   * @param orderState The order state to be updated.
   */
  public void updateOrder(UUID orderId, OrderState orderState) {
      for (Order order : orderList) {
        if (order.getId().equals(orderId)) {
          order.changeOrderStatus(orderState);
        }
      }
    }

  /**
   * Removes an order from the user's list of orders.
   *
   * @param orderId The order ID to be removed.
   */
  public void removeOrder(UUID orderId) {
    orderList.removeIf(order -> order.getId().equals(orderId));
  }

  /**
   * Gets the set of notifications of the user.
   *
   * @return The set of notifications of the suer.
   */
  public Set<String> getNotifications() {
    return notifications;
  }

  /**
   * Adds a notification to the set of notifications.
   *
   * @param notification The notification to be added.
   */
  public void addNotification(String notification) {
    notifications.add(notification);
  }

  /**
   * Removes a notification at the specified index from the user's notifications.
   *
   * @param i The index of the notification to be removed.
   */
  public void removeNotification(int i) {
    notifications.remove(i);
  }

  /**
   * Adds a metric and its value to the user's profile.
   *
   * @param metric The metric to be added
   * @param value The value associated with the metric.
   */
  public void addMetric(String metric, int value) {
    selectedMetrics.put(metric, new ArrayList<>(List.of(String.valueOf(value))));
  }

  /**
   * Removes a metric from the user's profile.
   *
   * @param metric The metric to be removed.
   */
  public void removeMetric(String metric) {
    selectedMetrics.remove(metric);
  }

  /**
   * Gets the selected metric from the user's profile.
   *
   * @return the selected metric from the user's profile.
   */
  public HashMap<String, List<String>> getSelectedMetrics() {
    return selectedMetrics;
  }

  /**
   * Sets the selected metric to the user's profile.
   *
   * @param chosenMetrics The selected metric to be set.
   */
  public void setSelectedMetrics(HashMap<String, List<String>> chosenMetrics) {
    this.selectedMetrics = chosenMetrics;
  }

  /**
   * Gets the list of liked reviews of the user's account.
   *
   * @return the list of liked reviews of the user's account.
   */
  public List<UUID> getLikedReviews() {
    return likedReviews;
  }

  /**
   * Sets a list of liked reviews to the user's account.
   *
   * @param likedReviews The list of liked reviews to be set.
   */
  public void setLikedReviews(List<UUID> likedReviews) {
    this.likedReviews = likedReviews;
  }

  /**
   * Adds a liked review to the user's account.
   *
   * @param reviewId The ID of the like review to be added.
   */
  public void addLikedReview(UUID reviewId) {
    this.likedReviews.add(reviewId);
  }

  /**
   * Removes a liked review from the user's account.
   *
   * @param reviewId The ID of the liked review to be removed.
   */
  public void removeLikedReview(UUID reviewId) {
    this.likedReviews.remove(reviewId);
  }

}