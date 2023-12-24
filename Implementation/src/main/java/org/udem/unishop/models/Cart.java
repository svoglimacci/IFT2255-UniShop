package org.udem.unishop.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;

/**
 * The Cart class represents a shopping cart containing items.
 * It is reponsible to manage the items contained and the transactional operations.
 */
public class Cart {

  @JsonProperty("items")
    private final List<CartItem> items;

  @JsonProperty("total_price")
    private double totalPrice;

  @JsonProperty("total_points")
    private int totalPoints;

    /**
     * Constructs an empty shopping cart.
     *
     * <p>The Cart constructor initializes an empty list of items. The total price and total points
     * are calculated based on the items in the cart.</p>
     */
    @JsonCreator
    public Cart() {
      this.items = new ArrayList<>();
        this.totalPrice = calculateTotalPrice();
        this.totalPoints = calculateTotalPoints();
    }

    /**
     * Calculates the total bonus points of all items in the shopping cart.
     *
     * @return The total bonus points in the shopping cart.
     */
    private int calculateTotalPoints() {
    int total = 0;
    for (CartItem item : this.items) {
      total += item.getPoints() * item.getQuantity();
    }
    return total;
  }

    /**
     * Gets the list of items in the shopping cart.
     *
     * @return The list of items in the shopping cart.
     */
    public List<CartItem> getItems() {
        return this.items;
    }

    /**
     * Calculates the total price of all items in the shopping cart.
     *
     * @return The total price of all items in the shopping cart.
     */
    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : this.items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    /**
     * Gets the total cost of all items in the shopping cart.
     *
     * @return The total cost of all items in the shopping cart.
     */
    @JsonIgnore
    public double getTotalCost() {
        return this.totalPrice;
    }

    /**
     * Adds a product to the shopping cart with the specified quantity.
     *
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product to be added.
     */
    public void addProduct(CartItem product, int quantity) {
        this.items.add(product);
        this.totalPrice = calculateTotalPrice();
    }

    /**
     * Removes an item from the shopping cart.
     *
     * @param cartItem The item to be removed from the cart.
     */
    public void removeItem(CartItem cartItem) {
        this.items.remove(cartItem);
        this.totalPrice = calculateTotalPrice();
      }

    /**
     * Adds a product to the shopping cart with a default quantity of 1.
     *
     * @param product The product to be added to the cart.
     */
    public void addItem(Product product) {
    CartItem cartItem = new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getBonusPoints());
    this.items.add(cartItem);
    this.totalPrice = calculateTotalPrice();
  }

    /**
     * Gets the total bonus points of all items in the shopping cart.
     *
     * @return The total bonus points in the shopping cart.
     */
    public int getTotalPoints() {
    return this.totalPoints;
  }
}