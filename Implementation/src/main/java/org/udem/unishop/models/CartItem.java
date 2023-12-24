package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents an item in a shopping cart.
 */
public class CartItem {
    private final UUID id;

    private final String name;

    private int quantity;

    private final double price;

    private int points;

    /**
     * Constructor for CartItem object.
     *
     * @param id The unique identifier of the item.
     * @param name The name of the item.
     * @param quantity The quantity of the item.
     * @param price The price of the item.
     * @param points The points associated with the item.
     */
    @JsonCreator
    public CartItem(@JsonProperty("id") UUID id,
                    @JsonProperty("name") String name,
                    @JsonProperty("quantity") int quantity,
                    @JsonProperty("price") double price,
              @JsonProperty("points") int points){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.points = points;
    }

    /**
     * Gets the name of the cart item.
     *
     * @return the name of the product item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the price of the cart item.
     *
     * @return the price of the cart item.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the quantity of the cart item.
     *
     * @param quantity of the cart item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the quantity of the cart item.
     *
     * @return The quantity of the cart item.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets the unique identifier of the item.
     *
     * @return the unique identifier of the item.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the points associated with the item.
     *
     * @return The points associated with the item.
     */
    public int getPoints() {
        return this.points;
    }
}