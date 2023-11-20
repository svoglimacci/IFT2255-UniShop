package models;


import java.util.Set;

public class ShoppingCart {
    private final Set<CartItem> items;

    private double totalPrice;


    public ShoppingCart( Set<CartItem> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    public Set<CartItem> getItems() {
        return this.items;
    }


    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : this.items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }


    public double getTotalCost() {
        return this.totalPrice;
    }


    public void addProduct(CartItem product, int quantity) {
        this.items.add(product);
        this.totalPrice = calculateTotalPrice();
    }
}