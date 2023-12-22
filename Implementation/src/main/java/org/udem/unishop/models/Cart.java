package org.udem.unishop.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;


public class Cart {

  @JsonProperty("items")
    private final List<CartItem> items;

  @JsonProperty("total_price")
    private double totalPrice;

  @JsonProperty("total_points")
    private int totalPoints;

@JsonCreator
    public Cart() {
      this.items = new ArrayList<>();
        this.totalPrice = calculateTotalPrice();
        this.totalPoints = calculateTotalPoints();
    }

  private int calculateTotalPoints() {
    int total = 0;
    for (CartItem item : this.items) {
      total += item.getPoints() * item.getQuantity();
    }
    return total;
  }

  public List<CartItem> getItems() {
        return this.items;
    }


    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : this.items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    @JsonIgnore
    public double getTotalCost() {
        return this.totalPrice;
    }


    public void addProduct(CartItem product, int quantity) {
        this.items.add(product);
        this.totalPrice = calculateTotalPrice();
    }

  public void removeItem(CartItem cartItem) {
    this.items.remove(cartItem);
    this.totalPrice = calculateTotalPrice();
  }

  public void addItem(Product product) {
    CartItem cartItem = new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getBonusPoints());
    this.items.add(cartItem);
    this.totalPrice = calculateTotalPrice();
  }

  public int getTotalPoints() {
    return this.totalPoints;
  }
}