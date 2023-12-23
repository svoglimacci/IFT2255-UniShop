package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CartItem {
    private final UUID id;

    private final String name;

    private int quantity;

    private final double price;

    private int points;

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

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public UUID getId() {
        return this.id;
    }

    public int getPoints() {
        return this.points;
    }
}