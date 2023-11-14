package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Buyer extends User {
    private String firstName;
    private String lastName;

    private List<UUID> purchases;

    private ShoppingCart cart;

    private List<Order> orders;


    @JsonCreator
    public Buyer(@JsonProperty("id") UUID id,
            @JsonProperty("username") String username,
                 @JsonProperty("password") String password,
                 @JsonProperty("email") String email,
                 @JsonProperty("address") String address,
                 @JsonProperty("firstName") String firstName,
                 @JsonProperty("lastName") String lastName,
                 @JsonProperty("phoneNumber") String phoneNumber,
                 @JsonProperty("isActive") boolean isActive,
                 @JsonProperty("dateCreated") Date dateCreated,
                 @JsonProperty("likes") Set<UUID> likes,
                 @JsonProperty("purchases") List<UUID> purchases,
                 @JsonProperty("cart") ShoppingCart cart) {
        super(id, username, password, email, address, phoneNumber, isActive, dateCreated, likes);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = cart;
        this.purchases = purchases;
        this.orders = new ArrayList<>();
    }


    // Getter methods

    public List<Order> getOrders() {
        return orders;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Setter methods

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public ShoppingCart getCart() {
        return this.cart;
    }

    public void addPurchase(UUID id) {
        this.purchases.add(id);
    }

    public List<UUID> getPurchases() {
        return purchases;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}