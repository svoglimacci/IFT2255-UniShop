package models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class ShoppingCart {
    private final Set<CartItem> items;

    private double totalPrice;

    @JsonCreator
    public ShoppingCart(@JsonProperty("items") Set<CartItem> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : this.items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public Set<CartItem> getProducts() {
        return this.items;
    }

    public double getTotalCost() {
        return this.totalPrice;
    }


    public void addProduct(CartItem product, int quantity) {
        this.items.add(product);
        this.totalPrice = calculateTotalPrice();
    }
}