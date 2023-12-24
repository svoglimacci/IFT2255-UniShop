package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Set;
import org.udem.unishop.utilities.OrderState;

/**
 * Represents an order placed by a buyer for one or more products.
 */
public class Order {


  @JsonProperty("id")
    private final UUID id;

  @JsonProperty("buyer_id")
    private final UUID buyerId;

  @JsonProperty("seller_id")
    private final UUID sellerId;

  @JsonProperty("orderDate")
    private final long orderDate = System.currentTimeMillis();

  @JsonProperty("products_id")
    private final Map<UUID, List<UUID>> productsId;

  @JsonProperty("price")
    private final double price;


    @JsonProperty("status")
    private OrderState status = OrderState.IN_PRODUCTION;

    @JsonProperty("issue")
    private Issue issue;


    /**
     * Constructs an Order object with specified properties.
     *
     * @param id The ID of the order.
     * @param buyerId The ID of the buyer associated with the order.
     * @param sellerId The ID of the seller associated with the products of the order.
     * @param productsId The ID of the ordered products.
     * @param price The total price of the order.
     */
    @JsonCreator
    public Order(
        @JsonProperty("id") UUID id, @JsonProperty("buyer_id") UUID buyerId, @JsonProperty("seller_id") UUID sellerId,
      @JsonProperty("products_id") Map<UUID, List<UUID>> productsId,
      @JsonProperty("price")double price) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productsId = productsId;
        this.price = price;
    }

    /**
     * Gets the unique identifier of the order.
     *
     * @return the order ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the unique identifier of the buyer placing the order.
     *
     * @return The buyer ID.
     */
    public UUID getBuyerId() {
        return buyerId;
    }

    /**
     * Gets a map of product IDs to lists of instance IDs indicating the items in the order.
     *
     * @return A map of product IDs to lists of instance IDs.
     */
    public Map<UUID, List<UUID>> getProductsId() {
        return productsId;
    }

    /**
     * Gets the total price of the order.
     *
     * @return The total price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the status of the order.
     *
     * @return The order status.
     */
    public OrderState getStatus() {
        return status;
    }

    /**
     * Adds a product to the order.
     *
     * @param productID The ID of the product to add.
     * @param instances A list of instance IDs representing the instances of the product in the order.
     */
    public void addProduct(UUID productID, List<UUID> instances){
        productsId.put(productID, instances);
    }

    /**
     * Sets the issue assicated with the order.
     *
     * @param issue The issue associated with the order.
     */
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    /**
     * Updates the order status to a new status.
     * @param newStatus The new order status.
     */
    public void changeOrderStatus(OrderState newStatus){
        this.status = newStatus;
    }

    /**
     * Gets the issue associated with the order.
     *
     * @return The issue associated with the order.
     */
    public Issue getIssue() {
        return issue;
    }

    /**
     * Gets the date when the order was placed.
     *
     * @return The order date in milliseconds.
     */
    public long getOrderDate() {
        return orderDate;
    }

    /**
     * Gets the unique identifier of the seller fulfilling the order.
     *
     * @return The seller ID.
     */
    public UUID getSellerId() {
        return sellerId;
    }

    /**
     * Gets the set of product IDs in the order.
     *
     * @return The set of product IDs.
     */
    @JsonIgnore
    public Set<UUID> getProducts() {
    return productsId.keySet();
  }

    /**
     * Gets the quantity of instances for a specific product in the order.
     *
     * @param id The ID of the product.
     * @return The quantity of instances for the product.
     */
     @JsonIgnore
     public int getProductQuantity(UUID id) {
    return productsId.get(id).size();
  }

    /**
     * Gets a list of all instance IDs in the order.
     *
     * @return A list of all instance IDs.
     */
      @JsonIgnore
      public List<UUID> getInstances() {
        List<UUID> instances = new ArrayList<>();
        for (UUID key : productsId.keySet()) {
          instances.addAll(productsId.get(key));
        }
        return instances;
      }

    /**
     * Sets the order status.
     * @param orderState The status to be set.
     */
    public void setStatus(OrderState orderState) {
    this.status = orderState;
  }
}