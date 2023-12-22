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

    public UUID getId() {
        return id;
    }


    public UUID getBuyerId() {
        return buyerId;
    }


    public Map<UUID, List<UUID>> getProductsId() {
        return productsId;
    }


    public double getPrice() {
        return price;
    }

    public OrderState getStatus() {
        return status;
    }


    public void addProduct(UUID productID, List<UUID> instances){
        productsId.put(productID, instances);
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void changeOrderStatus(OrderState newStatus){
        this.status = newStatus;
    }

    public Issue getIssue() {
        return issue;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    @JsonIgnore
  public Set<UUID> getProducts() {
    return productsId.keySet();
  }

  @JsonIgnore
  public int getProductQuantity(UUID id) {
    return productsId.get(id).size();
  }
  @JsonIgnore
  public List<UUID> getInstances() {
    List<UUID> instances = new ArrayList<>();
    for (UUID key : productsId.keySet()) {
      instances.addAll(productsId.get(key));
    }
    return instances;
  }
}