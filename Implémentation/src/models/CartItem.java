package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CartItem {
    private final UUID id;

    private final String name;

    private int quantity;

    private final double price;

    @JsonCreator
    public CartItem(@JsonProperty("id") UUID id,
                    @JsonProperty("name") String name,
                    @JsonProperty("quantity") int quantity,
                    @JsonProperty("price") double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String productToString() {
        return  " " + this.name + " | " +
                "Quantité: " + this.quantity + " | " +
                "Prix unitaire: " + this.price + "$ ";
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
}