package models;


import java.util.UUID;

public class CartItem {
    private final UUID id;

    private final String name;

    private int quantity;

    private final double price;


    public CartItem( UUID id,
                     String name,
                     int quantity,
                     double price) {
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
}